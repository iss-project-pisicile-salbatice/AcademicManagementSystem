package com.pisicilesalbatice.ams;


import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import com.pisicilesalbatice.ams.Service.StudentService;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestPolymorphism {

    public void TestAddUser(){
        StudentRepository studentRepository = null;
        StudentService studentService = new StudentService(studentRepository);
        Student student = new Student();
        studentService.addStudent(student);
        assert (student.getsId() == studentService.getStudents().get(0).getsId());
    }
}
