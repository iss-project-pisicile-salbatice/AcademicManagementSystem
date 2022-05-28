package com.pisicilesalbatice.ams.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class AcceptedOptional extends Course
{
    private Integer maximumStudents;

    @OneToMany(mappedBy = "acceptedOptional", cascade = CascadeType.ALL)
    private Set<OptionalRating> optionalRatings;

    public AcceptedOptional()
    {
        this.setOptional(true);
    }

    public AcceptedOptional(String courseName, Teacher teacher, YearSpeciality year, Integer maximumStudents)
    {
        super(courseName, teacher, year);
        this.setOptional(true);
        this.maximumStudents = maximumStudents;
    }

    public Integer getMaximumStudents()
    {
        return maximumStudents;
    }

    public void setMaximumStudents(Integer maximumStudents)
    {
        this.maximumStudents = maximumStudents;
    }

    @Override
    public String toString()
    {
        return "AcceptedOptional{" +
                "maximumStudents=" + maximumStudents +
                "} " + super.toString();
    }
}
