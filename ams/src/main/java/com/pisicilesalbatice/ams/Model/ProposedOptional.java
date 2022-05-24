package com.pisicilesalbatice.ams.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "proposed_optional")
public class ProposedOptional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int optionalId;

    private String optionalName;

    @ManyToOne
    @JoinColumn(name = "ys_id")
    private YearSpeciality yearSpeciality;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "t_id", nullable = false)
    private Teacher teacher;

    public ProposedOptional() {
    }

    public ProposedOptional(String optionalName, YearSpeciality yearSpeciality, Teacher teacher) {
        this.yearSpeciality = yearSpeciality;
        this.optionalName = optionalName;
        this.teacher = teacher;
    }

    public int getOptionalId() {
        return optionalId;
    }

    public void setOptionalId(int optionalId) {
        this.optionalId = optionalId;
    }

    public String getOptionalName() {
        return optionalName;
    }


    public YearSpeciality getYearSpeciality() {
        return yearSpeciality;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return "ProposedOptional{" +
                "optionalId=" + optionalId +
                ", oName='" + optionalName + '\'' +
                '}';
    }
}
