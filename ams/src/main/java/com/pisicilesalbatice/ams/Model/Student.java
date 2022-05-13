package com.pisicilesalbatice.ams.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int sId;

    private Date enrollmentDate;

    private String contract; //???

    @JsonManagedReference
    @OneToMany(mappedBy = "student")
    Set<Enrollment> enrollments;

    @JsonManagedReference
    @OneToMany(mappedBy = "student")
    Set<OptionalRating> optionalRatings;

    @ManyToMany
    @JoinTable(
            name = "student_group",
            joinColumns = @JoinColumn(name = "s_id"),
            inverseJoinColumns = @JoinColumn(name = "g_id"))
    private Set<Group> groups;

    public Student() {
    }

    public Student(Date enrollmentDate,  String contract) {
        this.enrollmentDate = enrollmentDate;
        this.contract = contract;
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
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public Set<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(Set<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public Set<OptionalRating> getOptionalRatings()
    {
        return optionalRatings;
    }

    public void setOptionalRatings(Set<OptionalRating> optionalRatings)
    {
        this.optionalRatings = optionalRatings;
    }

    @JsonIgnore
    public Set<Group> getGroups()
    {
        return groups;
    }
}
