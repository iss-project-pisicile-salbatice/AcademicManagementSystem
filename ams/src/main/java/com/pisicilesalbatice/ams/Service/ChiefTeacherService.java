package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Exceptions.Exceptions.StudentNotFoundException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.TeacherNotFoundException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.YearSpecialityNotFoundException;
import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Model.Teacher;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Repository.TeacherRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.time.Year;
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
        Teacher teacher = getTeacher(tId);
        YearSpeciality yearSpeciality = getYearSpeciality(ysId);
        return teacher.getCoursesFromGivenYear(yearSpeciality);
    }

    public List<Course> getCoursesTaughtByATeacher(Integer tId) {
        Teacher teacher = getTeacher(tId);
        return teacher.getCourses().stream().toList();
    }

    private YearSpeciality getYearSpeciality(Integer yearSpecialityID)
    {
        Optional<YearSpeciality> yearSpecialityOptional = this.yearSpecialityRepository.findById(yearSpecialityID);
        if(yearSpecialityOptional.isEmpty()) {
            throw new YearSpecialityNotFoundException("No year speciality with id " + yearSpecialityID + " was found");
        }
        return yearSpecialityOptional.get();
    }

    private Teacher getTeacher(Integer teacherID)
    {
        Optional<Teacher> teacherOptional = this.teacherRepository.findById(teacherID);
        if(teacherOptional.isEmpty()) {
            throw new TeacherNotFoundException("No teacher with id " + teacherID + " was found");
        }
        return teacherOptional.get();
    }

    public List<Teacher> getAllTeachers()
    {
        return this.teacherRepository.findAll();
    }
}
