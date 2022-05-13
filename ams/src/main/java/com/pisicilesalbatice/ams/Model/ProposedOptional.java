package com.pisicilesalbatice.ams.Model;

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

    @ManyToOne
    @JoinColumn(name = "t_id", nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "proposedOptional")
    private Set<OptionalRating> optionalRatings;

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

    public Set<OptionalRating> getOptionalRatings() {
        return optionalRatings;
    }

    public void setOptionalRatings(Set<OptionalRating> optionalRatings) {
        this.optionalRatings = optionalRatings;
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
