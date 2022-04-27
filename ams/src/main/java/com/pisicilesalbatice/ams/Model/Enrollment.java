package com.pisicilesalbatice.ams.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "enrollment")
public class Enrollment {
    @EmbeddedId
    private EnrollmentKey id;

    @JsonBackReference
    @ManyToOne
    @MapsId("sId")
    @JoinColumn(name = "s_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private Integer grade = null;

    public Enrollment() {
    }

    public Enrollment(EnrollmentKey id, Student student, Course course, Integer grade) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.grade = grade;
    }

    public Enrollment(EnrollmentKey id, Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public EnrollmentKey getId() {
        return id;
    }

    public void setId(EnrollmentKey id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", student=" + student +
                ", course=" + course +
                ", grade=" + grade +
                '}';
    }


}
