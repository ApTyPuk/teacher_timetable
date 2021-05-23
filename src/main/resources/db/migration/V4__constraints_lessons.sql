ALTER TABLE teachers_timetable.lessons
    ADD CONSTRAINT fk_teacher
    FOREIGN KEY (teachers_id)
    REFERENCES teachers_timetable.teachers(id);

ALTER TABLE teachers_timetable.lessons
    ADD CONSTRAINT fk_group
    FOREIGN KEY (group_id)
    REFERENCES teachers_timetable.student_groups(id);