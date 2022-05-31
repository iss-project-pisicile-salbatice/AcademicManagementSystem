package com.pisicilesalbatice.ams.Model.DTO;


import com.pisicilesalbatice.ams.Model.Course;

public class BasicDiscipline
{
    private final Course course;

    private Integer courseId;
    private String courseName;
    private String teacherName;
    protected String yearSpeciality;
    private boolean isOptional;

    public BasicDiscipline(Course course)
    {
        this.course = course;
        this.generateFields();
    }

    private void generateFields()
    {
        courseId = course.getCourseId();
        courseName = course.getCourseName();
        teacherName = "Prof. " + course.getTeacher().getDegree();
        yearSpeciality = String.valueOf(course.getYear().getYear()) + " " + course.getYear().getSpeciality();
        isOptional = course.isOptional();
    }

    public Integer getCourseId()
    {
        return courseId;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getTeacherName()
    {
        return teacherName;
    }

    public String getYearSpeciality()
    {
        return yearSpeciality;
    }

    public boolean isOptional()
    {
        return isOptional;
    }
}
