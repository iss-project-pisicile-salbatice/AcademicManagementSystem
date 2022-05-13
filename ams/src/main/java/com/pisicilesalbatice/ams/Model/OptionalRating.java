package com.pisicilesalbatice.ams.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name="optional_rating")
public class OptionalRating
{
    @EmbeddedId
    private OptionalRatingKey id;

    @JsonBackReference
    @ManyToOne
    @MapsId("sId")
    @JoinColumn(name = "s_id")
    private Student student;

    @JsonManagedReference
    @ManyToOne
    @MapsId("optionalId")
    @JoinColumn(name = "optional_id")
    private ProposedOptional proposedOptional;

    private Integer rating;

    public OptionalRating()
    {}

    public OptionalRating(Student student, ProposedOptional proposedOptional, Integer rating)
    {
        this.id = new OptionalRatingKey(student.getsId(), proposedOptional.getOptionalId());
        this.student = student;
        this.proposedOptional = proposedOptional;
        this.rating = rating;
    }

    public OptionalRating(OptionalRatingKey id, Student student, ProposedOptional proposedOptional, Integer rating)
    {
        this.id = id;
        this.student = student;
        this.proposedOptional = proposedOptional;
        this.rating = rating;
    }

    public OptionalRatingKey getId()
    {
        return id;
    }

    public void setId(OptionalRatingKey id)
    {
        this.id = id;
    }

    public Student getStudent()
    {
        return student;
    }

    public void setStudent(Student student)
    {
        this.student = student;
    }

    public ProposedOptional getProposedOptional()
    {
        return proposedOptional;
    }

    public void setProposedOptional(ProposedOptional proposedOptional)
    {
        this.proposedOptional = proposedOptional;
    }

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalRating that = (OptionalRating) o;
        return Objects.equals(id, that.id) && Objects.equals(student, that.student) && Objects.equals(proposedOptional, that.proposedOptional) && Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, student, proposedOptional, rating);
    }

    @Override
    public String toString()
    {
        return "OptionalRating{" +
                "id=" + id +
                ", student=" + student +
                ", proposedOptional=" + proposedOptional +
                ", rating=" + rating +
                '}';
    }
}
