package lv.kproject.teacher_timetable.core.web_ui.controllers.lesson;

import lv.kproject.teacher_timetable.core.requests.lesson.LessonAddRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupShowAllResponse;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonAddUpdateResponse;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherShowAllResponse;
import lv.kproject.teacher_timetable.core.services.group.StudentGroupShowAllService;
import lv.kproject.teacher_timetable.core.services.lesson.LessonAddService;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherShowAllService;
import lv.kproject.teacher_timetable.core.web_ui.controllers.rest.TeacherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LessonAddController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private LessonAddService serviceLesson;
    @Autowired
    private TeacherShowAllService serviceTeacher;
    @Autowired
    private StudentGroupShowAllService serviceStudentGroup;

    @GetMapping(value = "/lessonAdd")
    public String showLessonAddPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new LessonAddRequest());

        log.info("Received show all teachers request");
        TeacherShowAllResponse responseTeachers = serviceTeacher.showAllTeachers();
        modelMap.addAttribute("teachers", responseTeachers.getTeacherList());

        log.info("Received show all student groups request");
        StudentGroupShowAllResponse responseStudentGroups = serviceStudentGroup.showAllGroups();
        modelMap.addAttribute("groups", responseStudentGroups.getStudentGroupList());

        return "/lessonAdd";
    }

    @PostMapping(value = "/lessonAdd")
    public String processAddLessonRequest(
            @ModelAttribute(value = "request") LessonAddRequest request,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        log.info("Received create lesson request: {}", request);
        LessonAddUpdateResponse response = serviceLesson.save(request);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/lessonAdd";
        }

        redirectAttributes.addFlashAttribute("success", "Lesson successfully added");
        return "redirect:/lessonAdd";
    }

}
