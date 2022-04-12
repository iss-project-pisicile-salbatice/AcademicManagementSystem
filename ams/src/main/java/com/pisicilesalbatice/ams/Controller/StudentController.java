package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.Grade;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class StudentController
{
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    };

    @PostMapping("/students")
    Student newStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // Single item
    @GetMapping("/students/{id}")
    Student one(@PathVariable Integer id) {
        return studentService.findById(id);
    }

    @PutMapping("/students/{id}")
    Student replaceStudent(@RequestBody Student newStudent, @PathVariable Integer id) {
        return studentService.replaceStudent(newStudent, id);
    }

    @DeleteMapping("/students/{id}")
    void deleteStudent(@PathVariable Integer id) {
        studentService.deleteById(id);
    }

    @GetMapping("/students/grades/{id}")
    public Set<Grade> getGrades(@PathVariable Integer id) {
        return studentService.findGradesById(id);
    }
}
