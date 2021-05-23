package lv.kproject.teacher_timetable.core.web_ui.controllers.teacher;

import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.requests.teacher.TeacherUpdateRequest;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherAddUpdateResponse;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherFindByIdResponse;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherFindByIdService;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherUpdateService;
import lv.kproject.teacher_timetable.core.web_ui.controllers.rest.TeacherController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TeacherUpdateController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private TeacherUpdateService serviceUpdate;
    @Autowired
    private TeacherFindByIdService serviceFindById;

    @GetMapping(value = {"/teachers/{teacherId}/edit"})
    public String showEditTeacher(ModelMap modelMap, @PathVariable long teacherId) {
        CheckIdRequest request = new CheckIdRequest(teacherId);
        log.info("Received request to find teacher with id, {}", teacherId);
        TeacherFindByIdResponse response = serviceFindById.findTeacherById(request);

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/teacherManage";
        }

        modelMap.addAttribute("teacher", response.getTeacher());
        return "/teacherUpdate";
    }

    @PostMapping(value = {"/teachers/{teacherId}/edit"})
    public String updateTeacher(ModelMap modelMap,
                                @PathVariable long teacherId,
                                @ModelAttribute(value = "teacher") TeacherUpdateRequest teacherUpdateRequest,
                                RedirectAttributes redirectAttributes) {
        teacherUpdateRequest.setId(teacherId);
        log.info("Received request to update teacher entity: {}", teacherUpdateRequest);

        TeacherAddUpdateResponse response = serviceUpdate.update(teacherUpdateRequest);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/teacherUpdate";
        }

        redirectAttributes.addFlashAttribute("success", "Teacher successfully updated");
//        return "redirect:"  + String.valueOf(teacherUpdateRequest.getId());
//        return "redirect:/teacherUpdate";
        return "redirect:/teachers/{teacherId}/edit";

    }




}
