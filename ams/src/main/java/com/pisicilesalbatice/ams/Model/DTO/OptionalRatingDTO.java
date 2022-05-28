package com.pisicilesalbatice.ams.Model.DTO;

public class OptionalRatingDTO
{
    private final Integer acceptedOptionalId;
    private final Integer position;

    public OptionalRatingDTO(Integer acceptedOptionalId, Integer position)
    {
        this.acceptedOptionalId = acceptedOptionalId;
        this.position = position;
    }

    public Integer getAcceptedOptionalId()
    {
        return acceptedOptionalId;
    }

    public Integer getPosition()
    {
        return position;
    }
}
