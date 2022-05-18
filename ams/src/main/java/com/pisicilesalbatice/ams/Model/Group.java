package com.pisicilesalbatice.ams.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int gId;

    private String groupName;

    @ManyToOne
    @JoinColumn(name = "y_id")
    private YearSpeciality yId;

    @ManyToMany(mappedBy = "groups")
    private Set<Student> students;

    public Group() {
    }

    public Group(String groupName, YearSpeciality yId)
    {
        this.groupName = groupName;
        this.yId = yId;
    }

    public Group(int gId, String groupName) {
        this.gId = gId;
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @JsonIgnore
    public YearSpeciality getyId()
    {
        return yId;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "gId=" + gId +
                ", groupName='" + groupName + '\'' +
                ", yId=" + yId +
                ", students=" + students +
                '}';
    }
}
