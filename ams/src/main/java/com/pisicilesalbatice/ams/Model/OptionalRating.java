package com.pisicilesalbatice.ams.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
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
    private AcceptedOptional acceptedOptional;

    private Integer rating;

    private Date receivedDate;
    private Time receivedTime;

    public OptionalRating()
    {}

    public OptionalRating(OptionalRatingKey id, Student student, AcceptedOptional acceptedOptional, Integer rating)
    {
        this.id = id;
        this.student = student;
        this.acceptedOptional = acceptedOptional;
        this.rating = rating;
    }

    public OptionalRating(Student student, AcceptedOptional acceptedOptional, Integer rating, Date receivedDate, Time receivedTime)
    {
        this.id = new OptionalRatingKey(student.getsId(), acceptedOptional.getCourseId());
        this.student = student;
        this.acceptedOptional = acceptedOptional;
        this.rating = rating;
        this.receivedDate = receivedDate;
        this.receivedTime = receivedTime;
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

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    public Date getReceivedDate()
    {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate)
    {
        this.receivedDate = receivedDate;
    }

    public Time getReceivedTime()
    {
        return receivedTime;
    }

    public void setReceivedTime(Time receivedTime)
    {
        this.receivedTime = receivedTime;
    }

    public AcceptedOptional getAcceptedOptional()
    {
        return acceptedOptional;
    }

    public void setAcceptedOptional(AcceptedOptional acceptedOptional)
    {
        this.acceptedOptional = acceptedOptional;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalRating that = (OptionalRating) o;
        return Objects.equals(id, that.id) && Objects.equals(student, that.student) && Objects.equals(acceptedOptional, that.acceptedOptional) && Objects.equals(rating, that.rating) && Objects.equals(receivedDate, that.receivedDate) && Objects.equals(receivedTime, that.receivedTime);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, student, acceptedOptional, rating, receivedDate, receivedTime);
    }

    @Override
    public String toString()
    {
        return "OptionalRating{" +
                "id=" + id +
                ", student=" + student +
                ", acceptedOptional=" + acceptedOptional +
                ", rating=" + rating +
                ", receivedDate=" + receivedDate +
                ", receivedTime=" + receivedTime +
                '}';
    }
}
