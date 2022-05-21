package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.Group;
import com.pisicilesalbatice.ams.Model.Student;
import org.springframework.data.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupWithResultsDTO {
    private final Group group;
    private final List<Pair<Student, Float>> results;

    private String groupName;
    private List<Map<String, String>> studentsWithResults;

    public GroupWithResultsDTO(Group group, List<Pair<Student, Float>> results) {
        this.group = group;
        this.results = results;
        generateFields();
    }

    private void generateFields() {
        groupName = group.getGroupName();
        studentsWithResults = results.stream()
                .map((entry) -> {
                    int studentName = entry.getFirst().getsId(); // change this when we have the actual name
                    Float studentResult = entry.getSecond();
                    Map<String, String> studentWithResult = new HashMap<>();
                    studentWithResult.put("studentName", Integer.toString(studentName));
                    studentWithResult.put("result", Float.toString(studentResult));
                    return studentWithResult;
                })
                .collect(Collectors.toList());
    }

    public String getGroupName() {
        return groupName;
    }

    public List<Map<String, String>> getStudentsWithResults() {
        return studentsWithResults;
    }
}
