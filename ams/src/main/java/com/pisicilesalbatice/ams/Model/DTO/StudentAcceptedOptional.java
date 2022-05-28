package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.ProposedOptional;

public class StudentAcceptedOptional
{
    private final Course acceptedOptional;

    private Integer optionalID;
    private String courseName;
    private String teacherName;
    private String yearSpeciality;

    public StudentAcceptedOptional(Course acceptedOptional)
    {
        this.acceptedOptional = acceptedOptional;
        this.generateFields();
    }

    private void generateFields()
    {
        optionalID = acceptedOptional.getCourseId();
        courseName = acceptedOptional.getCourseName();
        teacherName = "Prof. " + acceptedOptional.getTeacher().getDegree();
        yearSpeciality = String.valueOf(acceptedOptional.getYear().getYear()) + " " + acceptedOptional.getYear().getSpeciality();
    }

    public Integer getOptionalID()
    {
        return optionalID;
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
