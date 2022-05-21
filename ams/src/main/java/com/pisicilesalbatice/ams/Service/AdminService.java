package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.Group;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Repository.GroupRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private YearSpecialityRepository yearSpecialityRepository;

    public Map<Group, List<Pair<Student,Float>>> getStudentsByGroup(){
        var groups = groupRepository.findAll();
        return groups.stream()
                .collect(Collectors.toMap(Function.identity(), Group::computeStudentResults));
    }
}
