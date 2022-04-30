package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private String courseName;

    @Column(columnDefinition = "boolean default false")
    protected boolean isOptional = false;

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

    public Course(String courseName, Teacher teacherId, YearSpeciality year) {
        this.courseName = courseName;
        this.teacher = teacherId;
        this.year = year;
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

    public YearSpeciality getYId() {
        return year;
    }

    public void setYId(YearSpeciality year) {
        this.year = year;
    }

    public boolean isOptional()
    {
        return isOptional;
    }

    public void setOptional(boolean optional)
    {
        isOptional = optional;
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
