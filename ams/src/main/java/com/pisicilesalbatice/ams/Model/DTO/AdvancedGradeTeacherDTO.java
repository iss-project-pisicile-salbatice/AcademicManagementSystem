package com.pisicilesalbatice.ams.Model.DTO;

import java.util.List;

public class AdvancedGradeTeacherDTO
{
    private String speciality;
    private List<BasicGrading> grades;

    public AdvancedGradeTeacherDTO(String speciality, List<BasicGrading> grades)
    {
        this.speciality = speciality;
        this.grades = grades;
    }

    public String getSpeciality()
    {
        return speciality;
    }

    public void setSpeciality(String speciality)
    {
        this.speciality = speciality;
    }

    public List<BasicGrading> getGrades()
    {
        return grades;
    }

    public void setGrades(List<BasicGrading> grades)
    {
        this.grades = grades;
    }
}