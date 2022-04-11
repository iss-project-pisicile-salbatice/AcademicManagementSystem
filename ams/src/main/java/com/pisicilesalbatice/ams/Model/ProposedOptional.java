package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;

@Entity
@Table(name="proposed_optional")
public class ProposedOptional {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int optionalId;
    private String oName;
    private double rating;
    @ManyToOne
    @JoinColumn(name = "tId",nullable = false)
    private Teacher teacher;
    public ProposedOptional(){}

    public ProposedOptional(int optionalId, String oName, double rating) {
        this.optionalId = optionalId;
        this.oName = oName;
        this.rating = rating;
    }

    public int getOptionalId() {
        return optionalId;
    }

    public void setOptionalId(int optionalId) {
        this.optionalId = optionalId;
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "ProposedOptional{" +
                "optionalId=" + optionalId +
                ", oName='" + oName + '\'' +
                ", rating=" + rating +
                '}';
    }


}
