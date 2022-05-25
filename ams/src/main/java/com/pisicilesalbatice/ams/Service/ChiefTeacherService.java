package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.Teacher;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Repository.TeacherRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ChiefTeacherService {
    private final TeacherRepository teacherRepository;
    private final YearSpecialityRepository yearSpecialityRepository;

    @Autowired
    public ChiefTeacherService(TeacherRepository teacherRepository, YearSpecialityRepository yearSpecialityRepository) {
        this.teacherRepository = teacherRepository;
        this.yearSpecialityRepository = yearSpecialityRepository;
    }


    public Map<Teacher, Pair<Float, Map<Course, Float>>> getTeachersWithCourseResults() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .collect(Collectors.toMap(Function.identity(), Teacher::computeResults));
        //this should be sorted. i guess this is easier done in the front end given the ridiculousness of the map.
    }

    public List<Course> getCoursesTaughtByATeacherInAGivenYear(Integer tId, Integer ysId) {
        Optional<Teacher> teacher = teacherRepository.findById(tId);
        Optional<YearSpeciality> yearSpeciality = yearSpecialityRepository.findById(ysId);
        if (teacher.isPresent() && yearSpeciality.isPresent()) {
            return teacher.get().getCoursesFromGivenYear(yearSpeciality.get());
        } else {
            throw new RuntimeException("Teacher or year speciality not found!");
        }
    }

    public List<Course> getCoursesTaughtByATeacher(Integer tId) {
        Optional<Teacher> teacher = teacherRepository.findById(tId);
        if (teacher.isPresent()) {
            return teacher.get().getCourses().stream().toList();
        } else {
            throw new RuntimeException("teacher not found!");
        }
    }
}
