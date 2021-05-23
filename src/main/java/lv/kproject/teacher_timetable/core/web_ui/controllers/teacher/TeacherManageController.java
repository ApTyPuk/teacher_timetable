package lv.kproject.teacher_timetable.core.web_ui.controllers.teacher;

import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherDeleteResponse;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherShowAllResponse;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherDeleteService;
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
public class TeacherManageController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherShowAllService serviceShowAll;
    @Autowired
    private TeacherDeleteService serviceDelete;

    @GetMapping(value = "/teacherManage")
    public String showTeacherShowAllPage(ModelMap modelMap) {
        TeacherShowAllResponse response = serviceShowAll.showAllTeachers();
        modelMap.addAttribute("teachers", response.getTeacherList());
        return "/teacherManage";
    }


    @PostMapping(value = "/teacherManage")
    public String deleteTeacher(
            @ModelAttribute(value = "deleteRequest") CheckIdRequest deleteRequest,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        log.info("Received request to delete teacher entity with id: {}", deleteRequest.getId());

        TeacherDeleteResponse response = serviceDelete.delete(deleteRequest);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/teacherAdd";
        }

        redirectAttributes.addFlashAttribute("success", "Teacher successfully deleted");
        return "redirect:/teacherManage";
    }

}
