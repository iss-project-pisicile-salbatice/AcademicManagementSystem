package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Curriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;
    private String courseName;
    @ManyToOne
    @JoinColumn(name = "tId",nullable = false)
    private Teacher teacher;
    private int ysId;
    @OneToMany(mappedBy = "course")
    Set<Grade>grades;

    public Curriculum(){
    }

    public Curriculum(int courseId, String courseName, Teacher teacherId, int ysId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacher = teacherId;
        this.ysId = ysId;
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

    public int getYsId() {
        return ysId;
    }

    public void setYsId(int ysId) {
        this.ysId = ysId;
    }

    @Override
    public String toString() {
        return "Curriculum{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherId=" + teacher +
                ", ysId=" + ysId +
                '}';
    }



//    @JoinColumn(name = "Tid")

}
