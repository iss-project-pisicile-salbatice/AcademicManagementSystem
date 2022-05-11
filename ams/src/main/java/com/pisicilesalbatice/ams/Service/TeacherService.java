package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.*;
import com.pisicilesalbatice.ams.Model.DTO.BasicDiscipline;
import com.pisicilesalbatice.ams.Model.DTO.BasicProposedOptional;
import com.pisicilesalbatice.ams.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeacherService
{
    private final TeacherRepository teacherRepository;
    private final ProposedOptionalRepository proposedOptionalRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final YearSpecialityRepository yearSpecialityRepository;

    public TeacherService(TeacherRepository teacherRepository, ProposedOptionalRepository proposedOptionalRepository, EnrollmentRepository enrollmentRepository, YearSpecialityRepository yearSpecialityRepository)
    {
        this.teacherRepository = teacherRepository;
        this.proposedOptionalRepository = proposedOptionalRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.yearSpecialityRepository = yearSpecialityRepository;
    }

    public List<ProposedOptional> getProposedOptionals(Integer teacher_id)
    {
        // todo: validate the teacher id
        Teacher teacher = teacherRepository.findById(teacher_id).get();

        return teacher.getOptionals().stream().toList();
    }

    public List<Course> getCourses(Integer teacher_id)
    {
        // todo: validate the teacher id
        Teacher teacher = teacherRepository.findById(teacher_id).get();

        return teacher.getCourses().stream().toList();
    }

    public void proposeOptional(Integer teacherID, Integer yearSpecialityID, String optionalName)
    {
        // todo: validate the teacher id
        Teacher teacher = teacherRepository.findById(teacherID).get();

        // todo: validate the year id
        YearSpeciality yearSpeciality = yearSpecialityRepository.findById(yearSpecialityID).get();

        // Check if the teacher already proposed 2 optionals
        if(teacher.getOptionals().size() >= 2) {
            throw new RuntimeException("Teacher with ID = " + teacherID + " already proposed 2 optionals");
        }
        // Check if an optional with the same name and speciality was added
        if(teacher.getOptionals().stream().anyMatch(optional ->
                optional.getYearSpeciality().equals(yearSpeciality) && optional.getName().equals(optionalName))) {
            throw new RuntimeException("Teacher with ID = " + teacherID + " already proposed this optional");
        }

        // Create a new optional and save it
        ProposedOptional newOptional = new ProposedOptional(optionalName, yearSpeciality, teacher);
        this.proposedOptionalRepository.save(newOptional);
        teacher.getOptionals().add(newOptional);
        teacherRepository.save(teacher);
    }

    public List<Enrollment> getCourseGrades(Integer teacherID) {
        // todo: validate the teacher id
        Teacher teacher = teacherRepository.findById(teacherID).get();

        List<Enrollment> allGrades = new ArrayList<>();
        for (Course course : teacher.getCourses())
        {
            allGrades = Stream.concat(allGrades.stream(), course.getEnrollments().stream()).collect(Collectors.toList());
        }

        return allGrades;
    }
}
