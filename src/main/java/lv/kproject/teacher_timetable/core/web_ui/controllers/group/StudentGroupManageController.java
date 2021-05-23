package lv.kproject.teacher_timetable.core.web_ui.controllers.group;

import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupDeleteResponse;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupShowAllResponse;
import lv.kproject.teacher_timetable.core.services.group.StudentGroupDeleteService;
import lv.kproject.teacher_timetable.core.services.group.StudentGroupShowAllService;
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
public class StudentGroupManageController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private StudentGroupShowAllService serviceShowAll;
    @Autowired
    private StudentGroupDeleteService serviceDelete;

    @GetMapping(value = "/groupManage")
    public String showStudentGroupShowAllPage(ModelMap modelMap) {
        StudentGroupShowAllResponse response = serviceShowAll.showAllGroups();
        modelMap.addAttribute("studentGroups", response.getStudentGroupList());
        return "/groupManage";
    }

    @PostMapping(value = "/groupManage")
    public String deleteStudentGroup(
            @ModelAttribute(value = "deleteRequest")CheckIdRequest deleteRequest,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        log.info("Received request to delete student group entity with id: {}", deleteRequest.getId());
        StudentGroupDeleteResponse response = serviceDelete.delete(deleteRequest);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/groupManage";
        }

        redirectAttributes.addFlashAttribute("success", "Student group successfully deleted");
        return "redirect:/groupManage";

    }

}
