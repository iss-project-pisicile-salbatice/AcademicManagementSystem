package com.pisicilesalbatice.ams.Model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="group_id")
public class GroupId {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int gId;
    private int groupNr;
    @OneToMany(mappedBy = "groupId")
    private Set<YearSpeciality> ysId;

    @OneToMany(mappedBy = "groupId")
    private Set<Student> students;

    public GroupId(){
    }

    public GroupId(int gId, int groupNr) {
        this.gId = gId;
        this.groupNr = groupNr;
    }
    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public int getGroupNr() {
        return groupNr;
    }

    public void setGroupNr(int group) {
        this.groupNr = group;
    }


    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "GroupId{" +
                "ysId=" + ysId +
                ", group=" + groupNr +
                '}';
    }
}
