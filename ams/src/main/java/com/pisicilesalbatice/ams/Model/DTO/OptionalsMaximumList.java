package com.pisicilesalbatice.ams.Model.DTO;

import java.util.List;

public class OptionalsMaximumList
{
    private Integer yearId;
    private List<OptionalMaximumDTO> optionalMaximumList;

    public OptionalsMaximumList(Integer yearId, List<OptionalMaximumDTO> optionalMaximumList)
    {
        this.yearId = yearId;
        this.optionalMaximumList = optionalMaximumList;
    }

    public Integer getYearId()
    {
        return yearId;
    }

    public void setYearId(Integer yearId)
    {
        this.yearId = yearId;
    }

    public List<OptionalMaximumDTO> getOptionalMaximumList()
    {
        return optionalMaximumList;
    }

    public void setOptionalMaximumList(List<OptionalMaximumDTO> optionalMaximumList)
    {
        this.optionalMaximumList = optionalMaximumList;
    }
}
