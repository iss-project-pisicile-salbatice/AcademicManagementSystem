package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;

@Entity
@Table(name = "grade")
public class Grade {
    @EmbeddedId
    GradeKey id;

    @ManyToOne
    @MapsId("sId")
    @JoinColumn(name = "s_id")
    private Student student;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    private int grade;

    public Grade(){
    }

    public Grade(GradeKey id, Student student, Course course, int grade) {
        this.id = id;
        this.student = student;
        this.course = course;
        this.grade = grade;
    }


    public GradeKey getId() {
        return id;
    }

    public void setId(GradeKey id) {
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

    public int getGrade() {
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
