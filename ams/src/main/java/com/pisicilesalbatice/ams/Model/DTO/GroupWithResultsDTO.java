package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.Group;
import com.pisicilesalbatice.ams.Model.Student;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class GroupWithResultsDTO {
    private final Group group;
    private final List<Pair<Student, Float>> results;

    private String groupName;
    private List<StudentWithResultsDTO> studentsWithResults;

    public GroupWithResultsDTO(Group group, List<Pair<Student, Float>> results) {
        this.group = group;
        this.results = results;
        generateFields();
    }

    private void generateFields() {
        groupName = group.getGroupName();
        studentsWithResults = results.stream()
                .map(entry -> new StudentWithResultsDTO(entry.getFirst(), entry.getSecond()))
                .collect(Collectors.toList());
    }

    public String getGroupName() {
        return groupName;
    }

    public List<StudentWithResultsDTO> getStudentsWithResults() {
        return studentsWithResults;
    }
}
