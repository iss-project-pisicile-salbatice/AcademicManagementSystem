package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.Grade;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Service.EnrollmentService;
import com.pisicilesalbatice.ams.Service.StudentService;
import com.pisicilesalbatice.ams.Utils.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;

@RestController
public class StudentController
{
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;

    @Autowired
    public StudentController(StudentService studentService, EnrollmentService enrollmentService)
    {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
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

    @GetMapping("/students/enroll")
    public List<YearSpeciality> getEnrollmentYears() {
        return enrollmentService.getAllYears();
    }

    @RequestMapping(
            value = "/students/enroll",
            method = RequestMethod.POST)
    public void enrollToYear(@RequestBody Map<String, Object> dataHashMap)
    {
        System.out.println("YOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        System.out.println(dataHashMap);

        Integer yearSpecialityID = (Integer) JsonConverter.jsonToObject((String) dataHashMap.get("yearSpecialityID"), Integer.class);
        Integer studentID = (Integer) JsonConverter.jsonToObject((String) dataHashMap.get("studentID"), Integer.class);
        String enrollmentDate = (String) JsonConverter.jsonToObject((String) dataHashMap.get("enrollmentDate"), String.class);

        Date enrollDate = Date.valueOf(enrollmentDate);
        enrollmentService.enrollStudent(studentID, yearSpecialityID, enrollDate);
    }

//    @PostMapping("/students/enroll/{id}/{date}")
//    void enrollToYear(@RequestBody Integer yearSpecialityID,
//                      @PathVariable(value = "id") Integer studentID,
//                      @PathVariable(value = "date") @DateTimeFormat(pattern = "yyyy-MM-dd") String enrollmentDate) {
//
//        // Convert string to date
//        Date enrollDate = Date.valueOf(enrollmentDate);
//        enrollmentService.enrollStudent(studentID, yearSpecialityID, enrollDate);
//    }
}
