package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.DTO.GroupWithResultsDTO;
import com.pisicilesalbatice.ams.Model.DTO.StudentWithResultsDTO;
import com.pisicilesalbatice.ams.Model.Group;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("admin/results")
    public List<GroupWithResultsDTO> getAllStudentResultsByGroup() {
        Map<Group, List<Pair<Student, Float>>> groupsWithResults = adminService.getAllStudentsByGroup();
        return groupsWithResults.entrySet().stream()
                .map(entry -> new GroupWithResultsDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @GetMapping("admin/results/{yearSpecialityId}")
    public List<StudentWithResultsDTO> getStudentsWithResultsByYear(@PathVariable(value = "yearSpecialityId") Integer yearSpecialityId) {
        var studentsWithResults = adminService.getStudentsByYear(yearSpecialityId);
        return studentsWithResults.stream()
                .map(studentFloatPair -> new StudentWithResultsDTO(studentFloatPair.getFirst(), studentFloatPair.getSecond()))
                .collect(Collectors.toList());
    }
}
