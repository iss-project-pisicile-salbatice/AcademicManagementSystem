package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;

@Entity
@Table(name="ys")
public class YearSpeciality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int year;
    private String speciality;
    @ManyToOne
    @JoinColumn(name = "g_id",nullable = false)
    private GroupId groupId;

    public YearSpeciality(){
    }

    public YearSpeciality(int year, String speciality, GroupId gId) {
        this.year = year;
        this.speciality = speciality;
        this.groupId = gId;
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


    public GroupId getGroupId() {
        return groupId;
    }

    public void setGroupId(GroupId groupId) {
        this.groupId = groupId;
    }
}
