package com.pisicilesalbatice.ams.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OptionalRatingKey implements Serializable
{
    @Column(name = "s_id")
    private int sId;

    @Column(name = "optional_id")
    private int optionalId;

    public OptionalRatingKey()
    {}

    public OptionalRatingKey(int sId, int optionalId)
    {
        this.sId = sId;
        this.optionalId = optionalId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionalRatingKey that = (OptionalRatingKey) o;
        return sId == that.sId && optionalId == that.optionalId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(sId, optionalId);
    }

    @Override
    public String toString()
    {
        return "OptionalRatingKey{" +
                "sId=" + sId +
                ", optionalId=" + optionalId +
                '}';
    }
}
