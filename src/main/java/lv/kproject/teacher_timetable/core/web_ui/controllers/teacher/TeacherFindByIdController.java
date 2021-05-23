//package lv.kproject.teacher_timetable.core.web_ui.controllers.teacher;
//
//import lv.kproject.teacher_timetable.core.responses.teacher.TeacherAddUpdateResponse;
//import lv.kproject.teacher_timetable.core.services.teacher.TeacherFindByIdService;
//import lv.kproject.teacher_timetable.core.web_ui.controllers.rest.TeacherController;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@Controller
//public class TeacherFindByIdController {
//
//    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);
//
//    @Autowired
//    private TeacherFindByIdService serviceFindById;
//
//    @GetMapping(value = "/teachers/{teacherId}")
//    public String getTeacherById(Model model, @PathVariable long contactId) {
//
//        TeacherAddUpdateResponse response = serviceFindById.findTeacherById(contactId);
//        model.addAttribute("teacher", response.getTeacher());
//        return "teacher";
//    }
//
//
//}
