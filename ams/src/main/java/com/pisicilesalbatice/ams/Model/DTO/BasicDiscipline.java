package com.pisicilesalbatice.ams.Model.DTO;


import com.pisicilesalbatice.ams.Model.Course;


public class BasicDiscipline
{
    private final Course course;

    private String courseName;
    private String teacherName;
    protected String yearSpeciality;

    public BasicDiscipline(Course course)
    {
        this.course = course;
        this.generateFields();
    }

    private void generateFields()
    {
        courseName = course.getCourseName();
        teacherName = "Vancea " + course.getTeacherId().getDegree();
        yearSpeciality = String.valueOf(course.getYId().getYear()) + " " + course.getYId().getSpeciality();
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
}
