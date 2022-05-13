package com.pisicilesalbatice.ams.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "course")
public class Course {
    @Column(columnDefinition = "bit default 0")
    protected boolean isOptional = false;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "t_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "y_id")
    private YearSpeciality year;

    @OneToMany(mappedBy = "course")
    private Set<Enrollment> enrollments;

    public Course() {
    }

    public Course(String courseName, Teacher teacher, YearSpeciality year) {
        this.courseName = courseName;
        this.teacher = teacher;
        this.year = year;
        enrollments = new HashSet<>();
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public YearSpeciality getYear() {
        return year;
    }

    public void setYear(YearSpeciality year) {
        this.year = year;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public Float computeGradesMean() {
        List<Integer> grades = enrollments.stream()
                .map(Enrollment::getGrade)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        int numberOfGrades = grades.size();
        if (numberOfGrades == 0) return (float) numberOfGrades;
        int sumOfGrades = grades.stream()
                .reduce(0, Integer::sum);
        return (float) sumOfGrades / numberOfGrades;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherId=" + teacher +
                ", year" + year +
                '}';
    }
}
