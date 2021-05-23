package lv.kproject.teacher_timetable.core.web_ui.controllers.lesson;

import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.requests.lesson.LessonUpdateRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupShowAllResponse;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonAddUpdateResponse;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonDaoFindByIdResponse;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherShowAllResponse;
import lv.kproject.teacher_timetable.core.services.group.StudentGroupShowAllService;
import lv.kproject.teacher_timetable.core.services.lesson.LessonObjectFindByIdService;
import lv.kproject.teacher_timetable.core.services.lesson.LessonUpdateService;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherShowAllService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LessonUpdateController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private LessonObjectFindByIdService serviceFindLessonById;
    @Autowired
    private LessonUpdateService serviceLessonUpdate;
    @Autowired
    private TeacherShowAllService serviceTeacherShowAll;
    @Autowired
    private StudentGroupShowAllService serviceStudentGroupShowAll;


    @GetMapping(value = "/lessons/{lessonId}/edit")
    public String showEditLessonGroup(ModelMap modelMap, @PathVariable long lessonId) {
        log.info("Received request to find lesson with id, {}", lessonId);

        LessonDaoFindByIdResponse responseLesson = serviceFindLessonById.findLessonById(new CheckIdRequest(lessonId));
        if (responseLesson.hasErrors()) {
            modelMap.addAttribute("errors", responseLesson.getErrors());
            return "/lessonManage";
        }
        modelMap.addAttribute("lesson", responseLesson.getLesson());

        log.info("Received show all teachers request");
        TeacherShowAllResponse responseTeachers = serviceTeacherShowAll.showAllTeachers();
        modelMap.addAttribute("teachers", responseTeachers.getTeacherList());

        log.info("Received show all student groups request");
        StudentGroupShowAllResponse responseStudentGroups = serviceStudentGroupShowAll.showAllGroups();
        modelMap.addAttribute("groups", responseStudentGroups.getStudentGroupList());

        return "/lessonUpdate";
    }

    @PostMapping(value = {"/lessons/{lessonId}/edit"})
    public String updateLesson(ModelMap modelMap,
                                     @PathVariable long lessonId,
                                     @ModelAttribute(value = "lesson") LessonUpdateRequest updateRequest,
                                     RedirectAttributes redirectAttributes) {
        log.info("Received request to update lesson group entity, {}", updateRequest);

        updateRequest.setId(lessonId);
        LessonAddUpdateResponse response = serviceLessonUpdate.update(updateRequest);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/lessonUpdate";
        }

        redirectAttributes.addFlashAttribute("success", "Lesson successfully updated");
        return "redirect:/lessons/{lessonId}/edit";
    }

}
