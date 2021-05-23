package lv.kproject.teacher_timetable.core.web_ui.controllers.group;

import lv.kproject.teacher_timetable.core.requests.group.StudentGroupUpdateRequest;
import lv.kproject.teacher_timetable.core.requests.id.CheckIdRequest;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupAddUpdateResponse;
import lv.kproject.teacher_timetable.core.responses.group.StudentGroupFindByIdResponse;
import lv.kproject.teacher_timetable.core.services.group.StudentGroupFindByIdService;
import lv.kproject.teacher_timetable.core.services.group.StudentGroupUpdateService;
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
public class StudentGroupUpdateController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);

    @Autowired
    private StudentGroupUpdateService serviceUpdate;
    @Autowired
    private StudentGroupFindByIdService serviceFindById;

    @GetMapping(value = {"/studentGroups/{groupId}/edit"})
    public String showEditStudentGroup(ModelMap modelMap, @PathVariable long groupId) {
        log.info("Received request to find student group with id, {}", groupId);

        StudentGroupFindByIdResponse response = serviceFindById.findGroupById(new CheckIdRequest(groupId));

        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/groupManage";
        }

        modelMap.addAttribute("studentGroup", response.getStudentGroup());
        return "/groupUpdate";
    }

    @PostMapping(value = {"/studentGroups/{groupId}/edit"})
    public String updateStudentGroup(ModelMap modelMap,
                                     @PathVariable long groupId,
                                     @ModelAttribute(value = "studentGroup")StudentGroupUpdateRequest studentGroupUpdateRequest,
                                     RedirectAttributes redirectAttributes) {
        studentGroupUpdateRequest.setId(groupId);
        log.info("Received request to update student group entity, {}", studentGroupUpdateRequest);

        StudentGroupAddUpdateResponse response = serviceUpdate.update(studentGroupUpdateRequest);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "/groupUpdate";
        }

        redirectAttributes.addFlashAttribute("success", "Student group successfully updated");
        return "redirect:/studentGroups/{groupId}/edit";
    }

}
