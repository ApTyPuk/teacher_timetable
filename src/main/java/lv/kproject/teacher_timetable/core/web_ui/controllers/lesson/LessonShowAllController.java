package lv.kproject.teacher_timetable.core.web_ui.controllers.lesson;

import lv.kproject.teacher_timetable.core.responses.lesson.LessonShowAllResponse;
import lv.kproject.teacher_timetable.core.services.lesson.LessonShowAllService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LessonShowAllController {

    private static final Logger log = LoggerFactory.getLogger(LessonShowAllController.class);

    @Autowired
    private LessonShowAllService service;

    @GetMapping(value = "/lessonShowAll")
    public String showLessonsAllPage(ModelMap modelMap) {
        log.info("Received show all lessons request");
        LessonShowAllResponse response = service.showAllLessons();
        modelMap.addAttribute("lessons", response.getLessonList());
        return "/lessonShowAll";
    }

}
