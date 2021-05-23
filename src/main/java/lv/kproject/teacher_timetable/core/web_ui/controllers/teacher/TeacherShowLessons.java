package lv.kproject.teacher_timetable.core.web_ui.controllers.teacher;

import lv.kproject.teacher_timetable.core.domain.Teacher;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonCalculateRateRequest;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonIntervalRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonCalculateRateResponse;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonShowAllResponse;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherFindByIdResponse;
import lv.kproject.teacher_timetable.core.services.lesson.LessonCalculateRateService;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherFindByIdService;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherFindLessons;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherFindLessonsInIntervalService;
import lv.kproject.teacher_timetable.core.web_ui.controllers.rest.TeacherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeacherShowLessons {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherFindLessons serviceTeacherFindLessons;
    @Autowired
    private TeacherFindLessonsInIntervalService serviceLessonsInInterval;
    @Autowired
    private TeacherFindByIdService serviceFindTeacherById;
    @Autowired
    private LessonCalculateRateService serviceCalculateRate;

    @GetMapping(value = "/teachers/{teacherId}/lessons")
    public String showTeachersLessons(ModelMap modelMap, @PathVariable long teacherId) {
        CheckIdRequest request = new CheckIdRequest(teacherId);

        log.info("Received request to find lessons for a teacher with id, {}", teacherId);

        LessonShowAllResponse lessonShowAllResponse = serviceTeacherFindLessons.findTeachersLessons(request);
        if (lessonShowAllResponse.hasErrors()) {
            modelMap.addAttribute("errors", lessonShowAllResponse.getErrors());
            return "/teacherShowAll";
        }

        TeacherFindByIdResponse findTeacherByIdResponse = serviceFindTeacherById.findTeacherById(new CheckIdRequest(teacherId));
        if (findTeacherByIdResponse.hasErrors()) {
            modelMap.addAttribute("errors", findTeacherByIdResponse.getErrors());
            return "/teacherShowAll";
        }

        modelMap.addAttribute("teachersLessons", lessonShowAllResponse.getLessonList());
        modelMap.addAttribute("teacher", findTeacherByIdResponse.getTeacher());
        modelMap.addAttribute("dateInterval", new LessonIntervalRequest());
        modelMap.addAttribute("intervalLessons", false);
        return "/teacherLessons";
    }

    @PostMapping(value = "/teachers/{teacherId}/lessons")
    public String postStartAndEndDatesForIntervalSearch(
            ModelMap modelMap,
            @PathVariable long teacherId,
            @ModelAttribute(value = "dateInterval") LessonIntervalRequest dateInterval) {

        log.info("Received request to find lessons in an interval from: " + dateInterval.getLessonStart().toString() +
                " till: " + dateInterval.getLessonEnd().toString() + " for a teacher with id, {}", teacherId);

        CheckIdRequest idRequest = new CheckIdRequest(teacherId);
        LessonShowAllResponse lessonShowAllResponse = serviceLessonsInInterval.findTeachersLessonsInInterval(dateInterval, idRequest);
        if (lessonShowAllResponse.hasErrors()) {
            modelMap.addAttribute("errors", lessonShowAllResponse.getErrors());
            //TODO
            return "/teachers/{teacherId}/lessons";
        }

        TeacherFindByIdResponse findTeacherByIdResponse = serviceFindTeacherById.findTeacherById(new CheckIdRequest(teacherId));
        if (findTeacherByIdResponse.hasErrors()) {
            modelMap.addAttribute("errors", findTeacherByIdResponse.getErrors());
            return "/teacherShowAll";
        }

        Teacher teacher = findTeacherByIdResponse.getTeacher();
        modelMap.addAttribute("teacher", teacher);

        modelMap.addAttribute("teachersLessonsInterval", lessonShowAllResponse.getLessonList());
        modelMap.addAttribute("intervalLessons", true);

        LessonCalculateRateRequest request = new LessonCalculateRateRequest(lessonShowAllResponse.getLessonList(), teacher);
        LessonCalculateRateResponse rateResponse =  serviceCalculateRate.calculateTeachersEarning(request);
        modelMap.addAttribute("earnedWage", rateResponse);

        return "/teacherLessons";
    }


}
