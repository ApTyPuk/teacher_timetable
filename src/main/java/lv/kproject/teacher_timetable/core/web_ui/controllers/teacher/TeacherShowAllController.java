package lv.kproject.teacher_timetable.core.web_ui.controllers.teacher;

import lv.kproject.teacher_timetable.core.responses.teacher.TeacherShowAllResponse;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherShowAllService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TeacherShowAllController {

    private static final Logger log = LoggerFactory.getLogger(TeacherShowAllController.class);

    @Autowired
    private TeacherShowAllService service;

    @GetMapping(value = "/teacherShowAll")
    public String showTeacherShowAllPage(ModelMap modelMap) {
        log.info("Received show all teachers request");
        TeacherShowAllResponse response = service.showAllTeachers();
        modelMap.addAttribute("teachers", response.getTeacherList());
        return "/teacherShowAll";
    }

}
