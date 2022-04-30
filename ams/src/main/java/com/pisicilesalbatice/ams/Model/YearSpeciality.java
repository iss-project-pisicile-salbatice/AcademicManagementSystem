package com.pisicilesalbatice.ams.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="year_speciality")
public class YearSpeciality {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int yId;

    private int year;

    private String speciality;

    @OneToMany(mappedBy = "yId")
    private Set<Group> group;

    @OneToMany(mappedBy = "year")
    private Set<Course> courses;

    @OneToMany(mappedBy = "yearSpeciality")
    private Set<ProposedOptional> proposedOptionals;

    public YearSpeciality(){
    }

    public YearSpeciality(int year, String speciality) {
        this.year = year;
        this.speciality = speciality;
    }

    public int getyId()
    {
        return yId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    @JsonIgnore
    public Set<Group> getStudentGroup()
    {
        return group;
    }

    @JsonIgnore
    public Set<Course> getCourses()
    {
        return courses;
    }

    @JsonIgnore
    public Set<ProposedOptional> getProposedOptionals()
    {
        return proposedOptionals;
    }

    @JsonIgnore
    public void setProposedOptionals(Set<ProposedOptional> proposedOptionals)
    {
        this.proposedOptionals = proposedOptionals;
    }

    @JsonIgnore
    public int getSpecialityHash() {
        return switch (speciality)
                {
                    case "Computer Science" -> 1;
                    case "Computer Engineering" -> 2;
                    default -> 9;
                };
    }
}
