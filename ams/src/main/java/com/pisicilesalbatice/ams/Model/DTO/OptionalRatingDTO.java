package com.pisicilesalbatice.ams.Model.DTO;

public class OptionalRatingDTO
{
    private final Integer proposedOptionalID;
    private final Integer position;

    public OptionalRatingDTO(Integer proposedOptionalID, Integer position)
    {
        this.proposedOptionalID = proposedOptionalID;
        this.position = position;
    }

    public Integer getProposedOptionalID()
    {
        return proposedOptionalID;
    }

    public Integer getPosition()
    {
        return position;
    }
}
