package com.pisicilesalbatice.ams.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class GradeKey implements Serializable {
    @Column(name = "s_id")
    private int sId;

    @Column(name = "course_id")
    private int courseId;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeKey gradeKey = (GradeKey) o;
        return sId == gradeKey.sId && courseId == gradeKey.courseId;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(sId, courseId);
    }

    @Override
    public String toString()
    {
        return "GradeKey{" +
                "sId=" + sId +
                ", courseId=" + courseId +
                '}';
    }
}
