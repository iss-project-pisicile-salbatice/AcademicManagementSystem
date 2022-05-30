package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.YearSpeciality;

public class YearDTO
{
    private int yearId;
    private int year;
    private String speciality;

    public YearDTO(YearSpeciality yearSpeciality)
    {
        this.yearId = yearSpeciality.getyId();
        this.year = yearSpeciality.getYear();
        this.speciality = yearSpeciality.getSpeciality();
    }

    public int getYearId()
    {
        return yearId;
    }

    public int getYear()
    {
        return year;
    }

    public String getSpeciality()
    {
        return speciality;
    }
}
