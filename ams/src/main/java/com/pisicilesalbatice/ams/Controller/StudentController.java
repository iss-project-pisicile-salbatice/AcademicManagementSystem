package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController
{
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    };
}
