package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="proposed_optional")
public class ProposedOptional {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int optionalId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "t_id",nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "proposedOptional")
    private Set<OptionalRating> optionalRatings;

    public ProposedOptional(){}

    public ProposedOptional(int optionalId, String name) {
        this.optionalId = optionalId;
        this.name = name;
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

    public Set<OptionalRating> getOptionalRatings()
    {
        return optionalRatings;
    }

    public void setOptionalRatings(Set<OptionalRating> optionalRatings)
    {
        this.optionalRatings = optionalRatings;
    }

    @Override
    public String toString() {
        return "ProposedOptional{" +
                "optionalId=" + optionalId +
                ", oName='" + name + '\'' +
                '}';
    }
}
