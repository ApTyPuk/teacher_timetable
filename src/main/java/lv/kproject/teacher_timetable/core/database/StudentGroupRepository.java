package lv.kproject.teacher_timetable.core.database;

import lv.kproject.teacher_timetable.core.domain.StudentGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentGroupRepository extends JpaRepository<StudentGroup, Long> {

    List<StudentGroup> findByName(String name);

}
