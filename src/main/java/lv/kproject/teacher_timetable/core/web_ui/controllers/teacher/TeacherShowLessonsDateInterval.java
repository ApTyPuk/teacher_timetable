/*
package lv.kproject.teacher_timetable.core.web_ui.controllers.teacher;

import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonIntervalRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonShowAllResponse;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherFindLessonsInIntervalService;
import lv.kproject.teacher_timetable.core.web_ui.controllers.rest.TeacherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Controller
public class TeacherShowLessonsDateInterval {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherFindLessonsInIntervalService service;

    @GetMapping(value = "/teachers/{teacherId}/lessons/{dateStart}/{dateEnd}")
    public String showTeachersLessons(
            ModelMap modelMap,
            @PathVariable long teacherId,
            @PathVariable LocalDateTime dateStart,
            @PathVariable LocalDateTime dateEnd
            ) {
        CheckIdRequest requestId = new CheckIdRequest(teacherId);

        log.info("Received request to find lessons for a teacher with id: " + teacherId +
                ". In time interval from: " + dateStart + ", till: " + dateEnd);


        LessonIntervalRequest lessonIntervalRequest = new LessonIntervalRequest(dateStart, dateEnd);
        LessonShowAllResponse response = service.findTeachersLessonsInInterval(lessonIntervalRequest, requestId);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/teachers/{teacherId}/lessons";
        }

        modelMap.addAttribute("teachersLessonsInterval", response.getLessonList());
        return "/teacherLessonsInterval";
    }
}
*/
