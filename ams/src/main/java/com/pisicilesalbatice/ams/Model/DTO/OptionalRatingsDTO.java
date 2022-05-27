package com.pisicilesalbatice.ams.Model.DTO;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class OptionalRatingsDTO
{
    private final Integer studentID;
    private final Integer yearSpecialityID;
    private final List<OptionalRatingDTO> ratings;
    private final Date receivedDate;
    private final Time receivedTime;

    public OptionalRatingsDTO(Integer studentID, Integer yearSpecialityID, List<OptionalRatingDTO> ratings, Date receivedDate, Time receivedTime)
    {
        this.studentID = studentID;
        this.yearSpecialityID = yearSpecialityID;
        this.ratings = ratings;
        this.receivedDate = receivedDate;
        this.receivedTime = receivedTime;
    }

    public Integer getStudentID()
    {
        return studentID;
    }

    public Integer getYearSpecialityID()
    {
        return yearSpecialityID;
    }

    public List<OptionalRatingDTO> getRatings()
    {
        return ratings;
    }

    public Date getReceivedDate()
    {
        return receivedDate;
    }

    public Time getReceivedTime()
    {
        return receivedTime;
    }
}
