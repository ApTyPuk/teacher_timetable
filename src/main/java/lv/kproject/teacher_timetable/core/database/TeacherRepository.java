package lv.kproject.teacher_timetable.core.database;

import lv.kproject.teacher_timetable.core.domain.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
