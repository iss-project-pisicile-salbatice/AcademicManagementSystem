package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.Enrollment;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Service.EnrollmentService;
import com.pisicilesalbatice.ams.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;

    @Autowired
    public StudentController(StudentService studentService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    ;

    @PostMapping("/students")
    Student newStudent(@RequestParam("contract") String contract,
                       @RequestParam("enrollmentDate") String enrollmentDate) {
        Date enrollDate = Date.valueOf(enrollmentDate);
        return studentService.addStudent(contract, enrollDate);
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
    public Set<Enrollment> getGrades(@PathVariable Integer id) {
        return studentService.findGradesById(id);
    }

    @GetMapping("/students/enroll")
    public List<YearSpeciality> getEnrollmentYears() {
        return enrollmentService.getAllYears();
    }

    @RequestMapping(
            value = "/students/enroll",
            method = RequestMethod.POST)
    public void enrollToYear(@RequestParam("yearSpecialityID") Integer yearSpecialityID,
                             @RequestParam("studentID") Integer studentID,
                             @RequestParam("enrollmentDate") String enrollmentDate) {
        Date enrollDate = Date.valueOf(enrollmentDate);
        enrollmentService.enrollStudent(studentID, yearSpecialityID, enrollDate);
    }

    @GetMapping("/students/courses/{sId}")
    public Set<Course> getStudentCourses(@PathVariable Integer sId){
        return studentService.getStudentCourses(sId);
    }
}
