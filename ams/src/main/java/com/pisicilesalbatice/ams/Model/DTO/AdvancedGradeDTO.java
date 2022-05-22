package com.pisicilesalbatice.ams.Model.DTO;

import java.util.List;

public class AdvancedGradeDTO
{
    private String speciality;
    private List<BasicGrade> grades;

    public AdvancedGradeDTO(String speciality, List<BasicGrade> grades)
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

    public List<BasicGrade> getGrades()
    {
        return grades;
    }

    public void setGrades(List<BasicGrade> grades)
    {
        this.grades = grades;
    }
}
