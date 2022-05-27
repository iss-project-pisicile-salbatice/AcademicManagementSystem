package com.pisicilesalbatice.ams.Model.DTO;

public class OptionalMaximumDTO
{
    private final Integer optionalId;
    private final Integer maximumStudents;

    public OptionalMaximumDTO(Integer optionalId, Integer maximumStudents)
    {
        this.optionalId = optionalId;
        this.maximumStudents = maximumStudents;
    }

    public Integer getOptionalId()
    {
        return optionalId;
    }

    public Integer getMaximumStudents()
    {
        return maximumStudents;
    }
}
