package com.pisicilesalbatice.ams.Model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.Enrollment;

public class BasicGrade extends BasicDiscipline
{
    private Enrollment enrollment;

    private Integer grade;

    public BasicGrade(Course course)
    {
        super(course);
    }

    public BasicGrade(Enrollment enrollment)
    {
        super(enrollment.getCourse());
        grade = enrollment.getGrade();
    }

    public Integer getGrade()
    {
        return grade;
    }

    @Override
    @JsonIgnore
    public String getYearSpeciality()
    {
        return yearSpeciality;
    }
}
