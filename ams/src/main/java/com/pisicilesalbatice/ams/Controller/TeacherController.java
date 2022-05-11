package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.DTO.BasicDiscipline;
import com.pisicilesalbatice.ams.Model.DTO.BasicGrade;
import com.pisicilesalbatice.ams.Model.DTO.BasicGrading;
import com.pisicilesalbatice.ams.Model.DTO.BasicProposedOptional;
import com.pisicilesalbatice.ams.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("teachers/courses/{teacher_id}")
    List<BasicDiscipline> getCourses(@PathVariable Integer teacher_id) {
        return this.teacherService.getCourses(teacher_id).stream().map(BasicDiscipline::new).collect(Collectors.toList());
    }

    @GetMapping("teachers/proposed_optionals/{teacher_id}")
    List<BasicProposedOptional> getProposedOptionals(@PathVariable Integer teacher_id) {
        return this.teacherService.getProposedOptionals(teacher_id).stream().map(BasicProposedOptional::new).collect(Collectors.toList());
    }

    @PostMapping("teachers/proposed_optionals")
    void proposeOptional(@RequestParam("teacherID") Integer teacherID,
                         @RequestParam("yearSpecialityID") Integer yearSpecialityID,
                         @RequestParam("optionalName") String optionalName) {
        this.teacherService.proposeOptional(teacherID, yearSpecialityID, optionalName);
    }

    @GetMapping("teachers/grades/{teacher_id}")
    List<BasicGrading> getGrades(@PathVariable Integer teacher_id) {
        return this.teacherService.getCourseGrades(teacher_id).stream().map(BasicGrading::new).collect(Collectors.toList());
    }
}
