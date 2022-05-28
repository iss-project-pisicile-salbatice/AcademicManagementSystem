package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.DTO.*;
import com.pisicilesalbatice.ams.Model.Teacher;
import com.pisicilesalbatice.ams.Service.ChiefOptionalService;
import com.pisicilesalbatice.ams.Service.ChiefTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ChiefTeacherController {
    private ChiefOptionalService chiefOptionalService;
    private ChiefTeacherService chiefTeacherService;

    @Autowired
    public ChiefTeacherController(ChiefOptionalService chiefOptionalService, ChiefTeacherService chiefTeacherService) {
        this.chiefOptionalService = chiefOptionalService;
        this.chiefTeacherService = chiefTeacherService;
    }

    @GetMapping("/chief/optionals")
    public List<BasicProposedOptional> getProposedOptionals() {
        return chiefOptionalService.getProposedOptionals().stream().map(BasicProposedOptional::new).collect(Collectors.toList());
    }

    @GetMapping("/chief/optionals/{year_id}")
    public List<BasicProposedOptional> getProposedOptionalsOfYear(@PathVariable Integer year_id) {
        return chiefOptionalService.getProposedOptionalsOfYear(year_id).stream().map(BasicProposedOptional::new).collect(Collectors.toList());
    }

    @PostMapping("/chief/optionals")
    public BasicAcceptedOptional acceptedOptional(@RequestParam("optionalId") Integer optionalId) {
        return new BasicAcceptedOptional(chiefOptionalService.acceptOptional(optionalId));
    }

    @PutMapping("/chief/optionals/maximum")
    public void setMaximumStudentsAll(@RequestBody OptionalsMaximumList optionalsList)
    {
        chiefOptionalService.setMaximumStudentsAll(optionalsList.getYearId(), optionalsList.getOptionalMaximumList().stream()
                .map(dto -> new com.pisicilesalbatice.ams.Model.Pair<>(dto.getOptionalId(), dto.getMaximumStudents())).collect(Collectors.toList()));
    }

    @PutMapping("/chief/optionals")
    public BasicAcceptedOptional setMaximumStudents(@RequestParam("courseId") Integer courseId,
                                                    @RequestParam("maximumStudents") Integer maximumStudents) {
        return new BasicAcceptedOptional(chiefOptionalService.setMaximumStudents(courseId, maximumStudents));
    }

    @GetMapping("/chief/optionals/assign/{year_id}")
    public void assignOptionals(@PathVariable Integer year_id) {
        this.chiefOptionalService.assignOptionals(year_id);
    }

    @GetMapping("/chief/teachers")
    public List<BasicTeacherWithCourseResults> getTeachersWithCourseResults() {
        Map<Teacher, Pair<Float, Map<Course, Float>>> teacherResults = chiefTeacherService.getTeachersWithCourseResults();
        return teacherResults.entrySet().stream()
                .map((entry) -> new BasicTeacherWithCourseResults(entry.getKey(),
                        entry.getValue().getSecond(),
                        entry.getValue().getFirst()))
                .collect(Collectors.toList());
    }

    @GetMapping("/chief/teachers/{teacherId}")
    public List<BasicDiscipline> getCoursesOfTeacher(@PathVariable(value = "teacherId") Integer teacherId) {
        return chiefTeacherService.getCoursesTaughtByATeacher(teacherId).stream()
                .map(BasicDiscipline::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/chief/teachers/{teacherId}/{yearId}")
    public List<BasicDiscipline> getCoursesOfTeacherInAYear(@PathVariable(value = "teacherId") Integer teacherId,
                                                            @PathVariable(value = "yearId") Integer yearId) {
        return chiefTeacherService.getCoursesTaughtByATeacherInAGivenYear(teacherId, yearId).stream()
                .map(BasicDiscipline::new)
                .collect(Collectors.toList());
    }
}
