package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;

@Entity
@Table(name="proposed_optional")
public class ProposedOptional {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int optionalId;

    private String name;

    private double rating;

    @ManyToOne
    @JoinColumn(name = "t_id",nullable = false)
    private Teacher teacher;

    public ProposedOptional(){}

    public ProposedOptional(int optionalId, String name, double rating) {
        this.optionalId = optionalId;
        this.name = name;
        this.rating = rating;
    }

    public int getOptionalId() {
        return optionalId;
    }

    public void setOptionalId(int optionalId) {
        this.optionalId = optionalId;
    }

    public String getoName() {
        return name;
    }

    public void setoName(String oName) {
        this.name = oName;
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
                ", oName='" + name + '\'' +
                ", rating=" + rating +
                '}';
    }


}
