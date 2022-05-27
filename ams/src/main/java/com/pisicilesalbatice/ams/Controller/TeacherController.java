package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.DTO.*;
import com.pisicilesalbatice.ams.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TeacherController
{
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService)
    {
        this.teacherService = teacherService;
    }

    @GetMapping("/teachers/courses/{teacher_id}")
    List<BasicDiscipline> getCourses(@PathVariable Integer teacher_id) {
        return this.teacherService.getCourses(teacher_id).stream().map(BasicDiscipline::new).collect(Collectors.toList());
    }

    @GetMapping("/teachers/proposed_optionals/{teacher_id}")
    List<BasicProposedOptional> getProposedOptionals(@PathVariable Integer teacher_id) {
        return this.teacherService.getProposedOptionals(teacher_id).stream().map(BasicProposedOptional::new).collect(Collectors.toList());
    }

    @PostMapping("/teachers/proposed_optionals")
    void proposeOptional(@RequestParam("teacherID") Integer teacherID,
                         @RequestParam("yearSpecialityID") Integer yearSpecialityID,
                         @RequestParam("optionalName") String optionalName) {
        this.teacherService.proposeOptional(teacherID, yearSpecialityID, optionalName);
    }

    @GetMapping("/teachers/grades/{teacher_id}")
    public List<AdvancedGradeTeacherDTO> getGrades(@PathVariable Integer teacher_id) {
        var gradesList = this.teacherService.getCourseGrades(teacher_id).stream().map(BasicGrading::new).collect(Collectors.toList());
        return gradesList.stream().collect(Collectors.groupingBy(BasicGrading::getYearSpeciality))
                .entrySet().stream().map(entry -> new AdvancedGradeTeacherDTO(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    @PostMapping("/teachers/grade_student")
    void gradeStudent(@RequestParam("teacherID") Integer teacherID,
                      @RequestParam("studentID") Integer studentID,
                      @RequestParam("courseID") Integer courseID,
                      @RequestParam("gradeValue") Integer gradeValue)
    {
        this.teacherService.gradeStudent(teacherID, studentID, courseID, gradeValue);
    }
}
