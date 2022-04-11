package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @OneToMany(mappedBy = "student")
    Set<Grade> grades;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int sId;

    private Date enrollmentDate;

    private String Contract; //???

    @ManyToMany
    @JoinTable(
            name = "groups",
            joinColumns = @JoinColumn(name = "s_id"),
            inverseJoinColumns = @JoinColumn(name = "g_id"))
    private Set<StudentGroup> groups;


    public Student() {
    }

    public Student(int sId, Date enrollmentDate, int year, String contract) {
        this.sId = sId;
        this.enrollmentDate = enrollmentDate;
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


    public String getContract() {
        return Contract;
    }

    public void setContract(String contract) {
        Contract = contract;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }
}
