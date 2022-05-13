package com.pisicilesalbatice.ams.Model.DTO;

import com.pisicilesalbatice.ams.Model.AcceptedOptional;

public class BasicAcceptedOptional extends BasicDiscipline {
    private Integer maximumStudents;

    public BasicAcceptedOptional(AcceptedOptional course) {
        super(course);
        maximumStudents = course.getMaximumStudents();
    }

    public Integer getMaximumStudents() {
        return maximumStudents;
    }
}
