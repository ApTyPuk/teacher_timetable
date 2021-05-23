package lv.kproject.teacher_timetable.core.web_ui.controllers.rest;

import lv.kproject.teacher_timetable.core.requests.teacher.TeacherAddRequest;
import lv.kproject.teacher_timetable.core.responses.teacher.TeacherAddUpdateResponse;
import lv.kproject.teacher_timetable.core.services.teacher.TeacherAddService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@Validated
@RestController
@RequestMapping(value = "/teachers")
public class TeacherController {

    private static final Logger log = LoggerFactory.getLogger(TeacherController.class);
    private final TeacherAddService teacherAddService;

    public TeacherController(TeacherAddService teacherAddService) {
        this.teacherAddService = teacherAddService;
    }

    @PostMapping
    public ResponseEntity<TeacherAddUpdateResponse> addTeacher(@Validated @RequestBody TeacherAddRequest request,
                                                               UriComponentsBuilder builder) {
        log.info("Received create teacher request: {}", request);
        TeacherAddUpdateResponse response = teacherAddService.save(request);
        return ResponseEntity.created(
                builder.path("/teachers/{id}")
                .buildAndExpand(response.getTeacher().getId())
                .toUri()
        )
                .body(response);
    }
}
