package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Teachers")
public class Teacher{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int tId;
    private String degree;
    @OneToMany(mappedBy = "teacher")
    private Set<ProposedOptional> optionals;
    @OneToMany(mappedBy = "teacher")
    private Set<Curriculum> courses;


    public Teacher(){
    }

    public Teacher(int tId, String degree) {
        this.tId = tId;
        this.degree = degree;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tId=" + tId +
                ", degree='" + degree + '\'' +
                '}';
    }


}
