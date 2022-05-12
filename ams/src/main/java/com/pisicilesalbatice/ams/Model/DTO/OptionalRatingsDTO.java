package com.pisicilesalbatice.ams.Model.DTO;

import java.util.List;

public class OptionalRatingsDTO
{
    private final Integer studentID;
    private final Integer yearSpecialityID;
    private final List<OptionalRatingDTO> ratings;

    public OptionalRatingsDTO(Integer studentID, Integer yearSpecialityID, List<OptionalRatingDTO> ratings)
    {
        this.studentID = studentID;
        this.yearSpecialityID = yearSpecialityID;
        this.ratings = ratings;
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
}
