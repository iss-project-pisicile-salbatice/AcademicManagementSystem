package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.Enrollment;

public class BasicGrading
{
    private String studentName;
    private String courseName;
    private String yearSpeciality;
    private Integer grade;

    private Enrollment enrollment;

    public BasicGrading(Enrollment enrollment)
    {
        this.enrollment = enrollment;
        this.generateFields();;
    }

    private void generateFields()
    {
        studentName = "Student " + enrollment.getStudent().getsId();
        courseName = enrollment.getCourse().getCourseName();
        yearSpeciality = String.valueOf(enrollment.getCourse().getYId().getYear()) + " " + enrollment.getCourse().getYId().getSpeciality();
        grade = enrollment.getGrade();
    }

    public String getStudentName()
    {
        return studentName;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public String getYearSpeciality()
    {
        return yearSpeciality;
    }

    public Integer getGrade()
    {
        return grade;
    }
}
