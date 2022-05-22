package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Exceptions.Exceptions.TeacherNotFoundException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.TeacherServiceException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.YearSpecialityNotFoundException;
import com.pisicilesalbatice.ams.Model.*;
import com.pisicilesalbatice.ams.Repository.EnrollmentRepository;
import com.pisicilesalbatice.ams.Repository.ProposedOptionalRepository;
import com.pisicilesalbatice.ams.Repository.TeacherRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;
    private final ProposedOptionalRepository proposedOptionalRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final YearSpecialityRepository yearSpecialityRepository;

    @Autowired
    private final GradeService gradeService;

    public TeacherService(TeacherRepository teacherRepository, ProposedOptionalRepository proposedOptionalRepository, EnrollmentRepository enrollmentRepository, YearSpecialityRepository yearSpecialityRepository, GradeService gradeService) {
        this.teacherRepository = teacherRepository;
        this.proposedOptionalRepository = proposedOptionalRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.yearSpecialityRepository = yearSpecialityRepository;
        this.gradeService = gradeService;
    }

    public List<ProposedOptional> getProposedOptionals(Integer teacherId) {
        Teacher teacher = getTeacher(teacherId);

        return teacher.getOptionals().stream().toList();
    }

    public List<Course> getCourses(Integer teacherId) {
        Teacher teacher = getTeacher(teacherId);

        return teacher.getCourses().stream().toList();
    }

    public void proposeOptional(Integer teacherID, Integer yearSpecialityID, String optionalName) {
        // Get the entities based on their IDs
        Teacher teacher = getTeacher(teacherID);
        YearSpeciality yearSpeciality = getYearSpeciality(yearSpecialityID);

        // Check if the teacher already proposed 2 optionals
        if (teacher.getOptionals().size() >= 2) {
            throw new TeacherServiceException("Teacher with ID = " + teacherID + " already proposed 2 optionals");
        }
        // Check if an optional with the same name and speciality was added
        if (teacher.getOptionals().stream().anyMatch(optional ->
                optional.getYearSpeciality().equals(yearSpeciality) && optional.getOptionalName().equals(optionalName))) {
            throw new TeacherServiceException("Teacher with ID = " + teacherID + " already proposed this optional");
        }

        // Create a new optional and save it
        ProposedOptional newOptional = new ProposedOptional(optionalName, yearSpeciality, teacher);
        this.proposedOptionalRepository.save(newOptional);
        teacher.getOptionals().add(newOptional);
        teacherRepository.save(teacher);
    }

    public List<Enrollment> getCourseGrades(Integer teacherID) {
        Teacher teacher = getTeacher(teacherID);

        List<Enrollment> allGrades = new ArrayList<>();
        for (Course course : teacher.getCourses()) {
            allGrades = Stream.concat(allGrades.stream(), course.getEnrollments().stream()).collect(Collectors.toList());
        }

        return allGrades;
    }

    public void gradeStudent(Integer teacherID, Integer studentID, Integer courseID, Integer gradeValue) {
        // Get the teacher
        Teacher teacher = getTeacher(teacherID);
        // Check if this is course is taught by the current teacher
        if (teacher.getCourses().stream().noneMatch(course -> course.getCourseId() == courseID)) {
            throw new TeacherServiceException("Teacher with id " + teacherID + " cannot grade course with id " + courseID);
        }

        // Grade the student
        this.gradeService.gradeStudent(studentID, courseID, gradeValue);
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
}
