package lv.kproject.teacher_timetable.core.web_ui.controllers.group;

import lv.kproject.teacher_timetable.core.responses.group.StudentGroupShowAllResponse;
import lv.kproject.teacher_timetable.core.services.group.StudentGroupShowAllService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentGroupShowAllController {

    private static final Logger log = LoggerFactory.getLogger(StudentGroupShowAllController.class);

    @Autowired
    private StudentGroupShowAllService service;

    @GetMapping(value = "/groupShowAll")
    public String showGroupShowAllPage(ModelMap modelMap) {
        log.info("Received show all groups request");
        StudentGroupShowAllResponse response = service.showAllGroups();
        modelMap.addAttribute("studentGroups", response.getStudentGroupList());
        return "/groupShowAll";
    }

}
