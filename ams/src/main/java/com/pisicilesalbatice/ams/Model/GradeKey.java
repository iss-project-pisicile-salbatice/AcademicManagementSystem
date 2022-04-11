package com.pisicilesalbatice.ams.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GradeKey implements Serializable {
    @Column(name = "s_id")
    private int sId;

    @Column(name = "course_id")
    private int courseId;
}
