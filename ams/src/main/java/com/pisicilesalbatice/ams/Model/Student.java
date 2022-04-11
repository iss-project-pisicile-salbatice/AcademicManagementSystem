package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name="Students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int sId;
    private Date enrollmentDate;
    private int year;
    private String Contract;
    @ManyToOne
    @JoinColumn(name = "g_id",nullable = false)
    private GroupId groupId;
    @OneToMany(mappedBy = "student")
    Set<Grade> grades;

    public Student(){
    }

    public Student(int sId, Date enrollmentDate, int year, GroupId groupNr, String contract) {
        this.sId = sId;
        this.enrollmentDate = enrollmentDate;
        this.year = year;
        this.groupId = groupNr;
        Contract = contract;
    }


    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public GroupId getGroupNr() {
        return groupId;
    }

    public void setGroupNr(GroupId group) {
        this.groupId = group;
    }

    public String getContract() {
        return Contract;
    }

    public void setContract(String contract) {
        Contract = contract;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sId=" + sId +
                ", enrollmentDate=" + enrollmentDate +
                ", year=" + year +
               // ", group=" + groupNr +
                ", Contract='" + Contract + '\'' +
                '}';
    }

//    public Set<GroupId> getGroups() {
//        return groups;
//    }
//
//    public void setGroups(Set<GroupId> groups) {
//        this.groups = groups;
//    }
}
