package com.pisicilesalbatice.ams.Controller;

import com.pisicilesalbatice.ams.Model.DTO.GroupWithResultsDTO;
import com.pisicilesalbatice.ams.Model.Group;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("admin/results/groups")
    public List<GroupWithResultsDTO> getGroupsWithResults(){
        Map<Group, List<Pair<Student,Float>>> groupsWithResults = adminService.getStudentsByGroup();
        return groupsWithResults.entrySet().stream()
                .map(entry -> new GroupWithResultsDTO(entry.getKey(),entry.getValue()))
                .collect(Collectors.toList());
    }
}
