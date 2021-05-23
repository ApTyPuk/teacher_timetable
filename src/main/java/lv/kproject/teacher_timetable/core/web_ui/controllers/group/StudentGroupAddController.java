package lv.kproject.teacher_timetable.core.web_ui.controllers.group;

import lv.kproject.teacher_timetable.core.requests.group.StudentGroupAddRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.group.StudentGroupAddService;
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
public class StudentGroupAddController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private StudentGroupAddService service;

    @GetMapping(value = "/groupAdd")
    public String showGroupAddPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new StudentGroupAddRequest());
        return "/groupAdd";
    }

    @PostMapping(value = "/groupAdd")
    public String processAddGroupRequest(
            @ModelAttribute(value = "request") StudentGroupAddRequest request,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        log.info("Received create group request: {}", request);
        StudentGroupAddUpdateResponse response = service.save(request);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/groupAdd";
        }

        redirectAttributes.addFlashAttribute("success", "Group successfully added");
        return "redirect:/groupAdd";
    }

}
