package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.Student;

public class StudentWithResultsDTO {
    private final Student student;
    private final Float result;

    private String studentName;


    public StudentWithResultsDTO(Student student, Float result) {
        this.student = student;
        this.result = result;
        generateFields();
    }

    private void generateFields() {
        studentName = Integer.toString(student.getsId()); // change when we have name
    }

    public String getStudentName() {
        return studentName;
    }

    public Float getResult() {
        return result;
    }
}
