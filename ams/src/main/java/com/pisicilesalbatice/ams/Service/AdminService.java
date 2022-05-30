package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.DTO.YearDTO;
import com.pisicilesalbatice.ams.Model.Group;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Repository.GroupRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private YearSpecialityRepository yearSpecialityRepository;

    public Map<Group, List<Pair<Student, Float>>> getAllStudentsByGroup() {
        var groups = groupRepository.findAll();
        return groups.stream()
                .collect(Collectors.toMap(Function.identity(), Group::computeStudentResults));
    }

    public List<Pair<Student, Float>> getStudentsByYear(Integer ysId) {
        Optional<YearSpeciality> yearSpecialityOptional = yearSpecialityRepository.findById(ysId);
        if (yearSpecialityOptional.isPresent()) {
            List<Pair<Student, Float>> students = new ArrayList<>();
            YearSpeciality yearSpeciality = yearSpecialityOptional.get();
            yearSpeciality.getStudentGroup().forEach((group) -> {
                group.getStudents().forEach((student -> students.add(Pair.of(student, student.computeAverageGrade()))));
            });
            return students;
        }
        else {
            throw new RuntimeException("Year speciality not found!");
        }
    }

    public List<YearSpeciality> getAllYears()
    {
        return this.yearSpecialityRepository.findAll();
    }
}
