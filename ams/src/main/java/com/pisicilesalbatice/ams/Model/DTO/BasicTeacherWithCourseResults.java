package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.Teacher;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BasicTeacherWithCourseResults {
    private final Teacher teacher;
    private final Map<Course, Float> courseResults;
    private final Float teacherResult;

    private String teacherName;
    private String teacherDegree;
    private List<DisciplineWithGrade> courseWithResults;

    public BasicTeacherWithCourseResults(Teacher teacher, Map<Course, Float> courseResults, Float teacherResult) {
        this.teacher = teacher;
        this.courseResults = courseResults;
        this.courseWithResults = new LinkedList<>();
        this.teacherResult = teacherResult;
        generateFields();
    }

    private void generateFields() {
        teacherName = String.valueOf(teacher.gettId()); //change this when we have the name (teacher extends user)
        teacherDegree = teacher.getDegree();
        for (Map.Entry<Course, Float> entry : courseResults.entrySet()) {
            courseWithResults.add(new DisciplineWithGrade(entry.getKey(), entry.getValue()));
        }
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherDegree() {
        return teacherDegree;
    }

    public Float getTeacherResult(){ return teacherResult; }

    public List<DisciplineWithGrade> getCourseWithResults() {
        return courseWithResults;
    }
}
