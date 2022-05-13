package com.pisicilesalbatice.ams.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tId;
    private String degree;
    @OneToMany(mappedBy = "teacher")
    private Set<ProposedOptional> optionals;
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;


    public Teacher() {
    }

    public Teacher(int tId, String degree) {
        this.tId = tId;
        this.degree = degree;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Pair<Float, Map<Course, Float>> computeResults() {
        Map<Course, Float> courseResults = courses.stream().collect(Collectors.toMap(Function.identity(), Course::computeGradesMean));
        int numberOfCourseResults = courseResults.entrySet().size();
        if (numberOfCourseResults == 0) {
            return Pair.of(0f, courseResults);
        }
        float sumOfCourseResults = courseResults.entrySet().stream().reduce(0f, (sum, entry) -> sum + entry.getValue(), Float::sum);
        return Pair.of(sumOfCourseResults / numberOfCourseResults, courseResults);
    }

    @JsonIgnore
    public List<Course> getCoursesFromGivenYear(YearSpeciality yearSpeciality) {
        return courses.stream()
                .filter((course -> course.getYear() == yearSpeciality))
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tId=" + tId +
                ", degree='" + degree + '\'' +
                '}';
    }


}
