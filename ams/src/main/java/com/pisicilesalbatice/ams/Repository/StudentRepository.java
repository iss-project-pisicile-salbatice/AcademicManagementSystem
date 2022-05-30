package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    Optional<Student> findByMainUser(User user);
}
