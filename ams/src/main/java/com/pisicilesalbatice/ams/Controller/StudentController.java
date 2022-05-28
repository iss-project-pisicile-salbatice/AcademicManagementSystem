package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.DTO.*;
import com.pisicilesalbatice.ams.Model.Pair;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Service.EnrollmentService;
import com.pisicilesalbatice.ams.Service.OptionalService;
import com.pisicilesalbatice.ams.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class StudentController {
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;
    private final OptionalService optionalService;

    @Autowired
    public StudentController(StudentService studentService, EnrollmentService enrollmentService, OptionalService optionalService)
    {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
        this.optionalService = optionalService;
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

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
    public List<AdvancedGradeDTO> getGrades(@PathVariable Integer id) {
        var gradeList = studentService.getStudentEnrollments(id).stream().map(BasicGrade::new).collect(Collectors.toSet());

        return gradeList.stream().collect(Collectors.groupingBy(BasicDiscipline::getYearSpeciality))
                .entrySet().stream().map(entry -> new AdvancedGradeDTO(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    @GetMapping("/students/enroll")
    public List<YearSpeciality> getEnrollmentYears() {
        // this.optionalService.DELETE_THIS();
        return enrollmentService.getAllYears();
    }

    @GetMapping("/students/enroll/{id}")
    public List<YearSpeciality> getStudentEnrollmentYears(@PathVariable Integer id) {
        return studentService.getYearSpecialities(id);
    }

    @RequestMapping(
            value = "/students/enroll",
            method = RequestMethod.POST)
    public void enrollToYear(@RequestParam("yearSpecialityID") Integer yearSpecialityID,
                             @RequestParam("studentID") Integer studentID,
                             @RequestParam("enrollmentDate") String enrollmentDate) {
        Date enrollDate = Date.valueOf(enrollmentDate);
        //System.out.println(enrollDate);
        //enrollmentService.enrollStudent(studentID, yearSpecialityID, enrollDate);
        System.out.println(yearSpecialityID);
        System.out.println(studentID);
        System.out.println(enrollmentDate);

    }

    @GetMapping("/students/courses_year/{year_id}")
    public List<BasicDiscipline> getYearSpecialitiesCourses(@PathVariable Integer year_id) {
        return enrollmentService.getYearCourses(year_id).stream().map(BasicDiscipline::new).collect(Collectors.toList());
    }

    @GetMapping("/students/courses/{sId}")
    public List<BasicDiscipline> getStudentCourses(@PathVariable Integer sId){
        return studentService.getStudentCourses(sId).stream().map(BasicDiscipline::new).collect(Collectors.toList());
    }

    @GetMapping("/students/proposed_optionals/{year_id}")
    public List<StudentAcceptedOptional> getAcceptedOptionals(@PathVariable Integer year_id){
        return optionalService.getAcceptedOptionals(year_id).stream().map(StudentAcceptedOptional::new).collect(Collectors.toList());
    }

    @PostMapping("/students/rate_optionals")
    public void sendOptionalRatings(@RequestBody OptionalRatingsDTO ratings) {
        this.optionalService.setOptionalRatings(
                ratings.getStudentID(),
                ratings.getYearSpecialityID(),
                ratings.getReceivedDate(),
                ratings.getReceivedTime(),
                ratings.getRatings().stream().map(dto -> new Pair<>(dto.getAcceptedOptionalId(), dto.getPosition())).collect(Collectors.toList())
                );
    }
}
