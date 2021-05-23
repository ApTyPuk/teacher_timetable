package lv.kproject.teacher_timetable.core.web_ui.controllers.teacher;

import lv.kproject.teacher_timetable.core.requests.teacher.TeacherAddRequest;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherAddService;
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
public class TeacherAddController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherAddService service;

    @GetMapping(value = "/teacherAdd")
    public String showTeacherAddPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new TeacherAddRequest());
        return "/teacherAdd";
    }

    @PostMapping("/teacherAdd")
    public String processAddTeacherRequest(
            @ModelAttribute(value = "request") TeacherAddRequest request,
            ModelMap modelMap,
            RedirectAttributes redirectAttributes) {
        log.info("Received create teacher request: {}", request);

        TeacherAddUpdateResponse response = service.save(request);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/teacherAdd";
        }

        redirectAttributes.addFlashAttribute("success", "Teacher successfully added");
        return "redirect:/teacherAdd";
    }


}
