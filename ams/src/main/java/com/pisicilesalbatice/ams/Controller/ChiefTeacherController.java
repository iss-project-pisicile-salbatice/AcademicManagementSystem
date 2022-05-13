package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.AcceptedOptional;
import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.DTO.BasicDiscipline;
import com.pisicilesalbatice.ams.Model.DTO.BasicTeacherWithCourseResults;
import com.pisicilesalbatice.ams.Model.ProposedOptional;
import com.pisicilesalbatice.ams.Model.Teacher;
import com.pisicilesalbatice.ams.Service.ChiefOptionalService;
import com.pisicilesalbatice.ams.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ChiefTeacherController {
    private ChiefOptionalService chiefOptionalService;
    private TeacherService teacherService;

    @Autowired
    public ChiefTeacherController(ChiefOptionalService chiefOptionalService, TeacherService teacherService) {
        this.chiefOptionalService = chiefOptionalService;
        this.teacherService = teacherService;
    }

    @GetMapping("/chief/optionals")
    public List<ProposedOptional> getProposedOptionals() {
        return chiefOptionalService.getProposedOptionals();
    }

    @PostMapping("/chief/optionals")
    public AcceptedOptional acceptedOptional(@RequestParam("optionalId") Integer optionalId) {
        return chiefOptionalService.acceptOptional(optionalId);
    }

    @PutMapping("/chief/optionals")
    public AcceptedOptional setMaximumStudents(@RequestParam("courseId") Integer courseId,
                                               @RequestParam("maximumStudents") Integer maximumStudents) {
        return chiefOptionalService.setMaximumStudents(courseId, maximumStudents);
    }

    @GetMapping("/chief/teachers")
    public List<BasicTeacherWithCourseResults> getTeachersWithCourseResults() {
        Map<Teacher, Pair<Float, Map<Course, Float>>> teacherResults = teacherService.getTeachersWithCourseResults();
        return teacherResults.entrySet().stream()
                .map((entry) -> new BasicTeacherWithCourseResults(entry.getKey(),
                        entry.getValue().getSecond(),
                        entry.getValue().getFirst()))
                .collect(Collectors.toList());
    }

    @GetMapping("/chief/teachers/{teacherId}")
    public List<BasicDiscipline> getCoursesOfTeacher(@PathVariable(value = "teacherId") Integer teacherId) {
        return teacherService.getCoursesTaughtByATeacher(teacherId).stream()
                .map(BasicDiscipline::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/chief/teachers/{teacherId}/{yearId}")
    public List<BasicDiscipline> getCoursesOfTeacherInAYear(@PathVariable(value = "teacherId") Integer teacherId,
                                                            @PathVariable(value = "yearId") Integer yearId) {
        return teacherService.getCoursesTaughtByATeacherInAGivenYear(teacherId, yearId).stream()
                .map(BasicDiscipline::new)
                .collect(Collectors.toList());
    }
}
