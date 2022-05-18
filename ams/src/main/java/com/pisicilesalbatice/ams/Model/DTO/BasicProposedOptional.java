package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.ProposedOptional;

public class BasicProposedOptional
{
    private final ProposedOptional proposedOptional;

    private Integer optionalID;
    private String courseName;
    private String teacherName;
    private String yearSpeciality;

    public BasicProposedOptional(ProposedOptional proposedOptional)
    {
        this.proposedOptional = proposedOptional;
        this.generateFields();
    }

    private void generateFields()
    {
        optionalID = proposedOptional.getOptionalId();
        courseName = proposedOptional.getOptionalName();
        teacherName = "Prof. " + proposedOptional.getTeacher().getDegree();
        yearSpeciality = String.valueOf(proposedOptional.getYearSpeciality().getYear()) + " " + proposedOptional.getYearSpeciality().getSpeciality();
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
