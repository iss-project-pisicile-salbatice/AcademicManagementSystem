package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.ProposedOptional;

public class BasicProposedOptional
{
    private final ProposedOptional proposedOptional;

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
        courseName = proposedOptional.getOptionalName();
        teacherName = "Prof. " + proposedOptional.getTeacher().getDegree();
        yearSpeciality = String.valueOf(proposedOptional.getYearSpeciality().getYear()) + " " + proposedOptional.getYearSpeciality().getSpeciality();
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
