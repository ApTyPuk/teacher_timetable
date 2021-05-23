package lv.kproject.teacher_timetable.core.web_ui.controllers.container;

import lv.kproject.teacher_timetable.core.web_ui.controllers.rest.TeacherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContainerWelcomeController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);


    @GetMapping(value = "/containerWelcome")
//    public String showAddTeacherPage(ModelMap modelMap) {
    public String showAddTeacherPage() {
//        modelMap.addAttribute("request", new AddTeacherRequest());
        return "/containerWelcome";
    }


}
