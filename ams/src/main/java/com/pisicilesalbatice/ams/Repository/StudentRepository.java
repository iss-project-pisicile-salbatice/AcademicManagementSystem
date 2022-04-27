package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
