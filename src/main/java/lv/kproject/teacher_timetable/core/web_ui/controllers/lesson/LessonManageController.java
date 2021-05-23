package lv.kproject.teacher_timetable.core.web_ui.controllers.lesson;

import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonDeleteResponse;
import lv.kproject.teacher_timetable.core.responses.lesson.LessonShowAllResponse;
import lv.kproject.teacher_timetable.core.services.lesson.LessonDeleteService;
import lv.kproject.teacher_timetable.core.services.lesson.LessonShowAllService;
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
public class LessonManageController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private LessonShowAllService serviceShowAll;
    @Autowired
    private LessonDeleteService serviceDelete;

    @GetMapping(value = "/lessonManage")
    public String showLessonShowAllPage(ModelMap modelMap) {
        LessonShowAllResponse response = serviceShowAll.showAllLessons();
        modelMap.addAttribute("lessons", response.getLessonList());
        return "/lessonManage";
    }

    @PostMapping(value = "/lessonManage")
    public String deleteStudentGroup(
            @ModelAttribute(value = "deleteRequest")CheckIdRequest deleteRequest,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        log.info("Received request to delete lesson entity with id: {}", deleteRequest.getId());
        LessonDeleteResponse response = serviceDelete.delete(deleteRequest);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/lessonManage";
        }

        redirectAttributes.addFlashAttribute("success", "Lesson successfully deleted");
        return "redirect:/lessonManage";

    }

}
