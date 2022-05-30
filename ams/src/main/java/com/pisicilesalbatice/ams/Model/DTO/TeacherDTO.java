package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.Teacher;

public class TeacherDTO
{
    private Integer teacherId;
    private String name;
    private String degree;
    private String email;

    public TeacherDTO(Teacher teacher)
    {
        if(teacher.getMainUser() != null)
        {
            this.name = teacher.getMainUser().getUsername();
            this.email = teacher.getMainUser().getEmail();
        }
        else
        {
            this.name = "Prof. T";
            this.email = "proft@catuni.edu";
        }
        this.teacherId = teacher.gettId();
        this.degree = teacher.getDegree();
    }

    public Integer getTeacherId()
    {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId)
    {
        this.teacherId = teacherId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDegree()
    {
        return degree;
    }

    public void setDegree(String degree)
    {
        this.degree = degree;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
