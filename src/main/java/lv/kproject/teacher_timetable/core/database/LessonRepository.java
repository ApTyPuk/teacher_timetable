package lv.kproject.teacher_timetable.core.database;

import lv.kproject.teacher_timetable.core.domain.Lesson;
import lv.kproject.teacher_timetable.core.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findLessonsByTeacherIs(Teacher teacher);

    List<Lesson> findAllByLessonDateBetween(LocalDateTime start, LocalDateTime finish);

    List<Lesson> findAllByLessonDateBetweenAndTeacherIs(LocalDateTime start, LocalDateTime finish, Teacher teacher);

}
