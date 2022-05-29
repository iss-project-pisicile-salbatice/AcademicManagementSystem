package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.Teacher;
import com.pisicilesalbatice.ams.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByMainUser(User user);
}
