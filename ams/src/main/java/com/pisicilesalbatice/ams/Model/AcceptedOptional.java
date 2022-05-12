package com.pisicilesalbatice.ams.Model;

import javax.persistence.Entity;

@Entity
public class AcceptedOptional extends Course
{
    private Integer maximumStudents;

    public AcceptedOptional()
    {
        this.setOptional(true);
    }

    public AcceptedOptional(String courseName, Teacher teacherId, YearSpeciality year, Integer maximumStudents)
    {
        super(courseName, teacherId, year);
        this.setOptional(true);
        this.maximumStudents = maximumStudents;
    }

    public Integer getMaximumStudents()
    {
        return maximumStudents;
    }

    public void setMaximumStudents(Integer maximumStudents)
    {
        this.maximumStudents = maximumStudents;
    }

    @Override
    public String toString()
    {
        return "AcceptedOptional{" +
                "maximumStudents=" + maximumStudents +
                "} " + super.toString();
    }
}
