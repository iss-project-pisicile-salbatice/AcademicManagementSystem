package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.Course;

public class DisciplineWithGrade {
    private final Course course;

    private String courseName;
    private Float resultsMean;

    public DisciplineWithGrade(Course course, Float results) {
        this.course = course;
        this.resultsMean = results;
        generateFields();
    }

    private void generateFields(){
        courseName = course.getCourseName();
    }

    public String getCourseName() {
        return courseName;
    }

    public Float getResultsMean() {
        return resultsMean;
    }
}
