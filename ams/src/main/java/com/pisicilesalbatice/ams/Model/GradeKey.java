package com.pisicilesalbatice.ams.Model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GradeKey implements Serializable {
    @Column(name = "sId")
    private int studentId;

    @Column(name = "courseId")
    private int courseId;
}
