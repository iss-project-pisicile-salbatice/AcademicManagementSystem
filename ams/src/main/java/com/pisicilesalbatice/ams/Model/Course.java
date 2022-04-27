package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="course")
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int courseId;

    private String courseName;

    @ManyToOne
    @JoinColumn(name = "t_id",nullable = false)
    private Teacher teacher;

    private int yId;

    @OneToMany(mappedBy = "course")
    Set<Grade>grades;

    public Course(){
    }

    public Course(int courseId, String courseName, Teacher teacherId, int yId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacherId;
        this.yId = yId;
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

    public Teacher getTeacherId() {
        return teacher;
    }

    public void setTeacherId(Teacher teacherId) {
        this.teacher = teacherId;
    }

    public int getYId() {
        return yId;
    }

    public void setYId(int ysId) {
        this.yId = ysId;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherId=" + teacher +
                ", ysId=" + yId +
                '}';
    }



//    @JoinColumn(name = "Tid")

}
