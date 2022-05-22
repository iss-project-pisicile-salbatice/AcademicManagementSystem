package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Exceptions.Exceptions.EnrollmentServiceException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.StudentNotFoundException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.YearSpecialityNotFoundException;
import com.pisicilesalbatice.ams.Model.*;
import com.pisicilesalbatice.ams.Model.DTO.BasicDiscipline;
import com.pisicilesalbatice.ams.Repository.EnrollmentRepository;
import com.pisicilesalbatice.ams.Repository.StudentGroupRepository;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EnrollmentService
{
    private static final int MAX_GROUP_SIZE = 25;
    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;
    private final YearSpecialityRepository yearSpecialityRepository;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(StudentRepository studentRepository, StudentGroupRepository studentGroupRepository, YearSpecialityRepository yearSpecialityRepository, EnrollmentRepository enrollmentRepository)
    {
        this.studentRepository = studentRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.yearSpecialityRepository = yearSpecialityRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<YearSpeciality> getAllYears()
    {
        return this.yearSpecialityRepository.findAll();
    }

    private Group createNewYearGroup(YearSpeciality yearSpeciality) {
        Set<Group> yearGroups = yearSpeciality.getStudentGroup();
        // Create a new group and add the student to it
        int lastGroupName = yearGroups.stream()
                .mapToInt(group -> Integer.parseInt(group.getGroupName()))
                .max().orElse(yearSpeciality.getyId() * 100 + yearSpeciality.getYear() * 10);
        Group newGroup = new Group(String.valueOf(lastGroupName + 1), yearSpeciality);
        this.studentGroupRepository.save(newGroup);
        return newGroup;
    }

    public void enrollStudent(Integer studentID, Integer yearSpecialityID, Date enrollDate)
    {
        // Get the student and year speciality based on IDs
        Student student = getStudent(studentID);
        YearSpeciality year = getYearSpeciality(yearSpecialityID);

        // Validate the student enrollment try
        validateStudentEnrollment(student, year);

        // Get the groups of the selected year
        Set<Group> yearGroups = year.getStudentGroup();

        // Check if there are any groups with less than the maximum capacity
        Optional<Group> studentGroup = yearGroups.stream().filter(group -> group.getStudents().size() < MAX_GROUP_SIZE).findFirst();
        if(studentGroup.isPresent()) {
            // Add the current student to the found group
            student.getGroups().add(studentGroup.get());
        } else {
            // Create a new group and add the student in it
            student.getGroups().add(this.createNewYearGroup(year));
        }

        // Save the enrollment date and update the student in the repository
        student.setEnrollmentDate(enrollDate);
        this.addMandatoryCourses(studentID, yearSpecialityID);
        this.studentRepository.save(student);
    }

    public void addMandatoryCourses(Integer studentID, Integer yearSpecialityID) {
        // Get the student and year speciality based on IDs
        Student student = getStudent(studentID);
        YearSpeciality year = getYearSpeciality(yearSpecialityID);

        // Get all the courses for the year speciality
        Set<Course> courses = year.getCourses();
        // Save the new courses in the enrollment repo
        courses.stream()
                .filter(course -> !course.isOptional())
                .forEach(course -> enrollmentRepository.save(new Enrollment(student, course)));
    }

    public List<Course> getYearCourses(Integer year_id)
    {
        YearSpeciality yearSpeciality = getYearSpeciality(year_id);
        return yearSpeciality.getCourses().stream().toList();
    }

    private YearSpeciality getYearSpeciality(Integer yearSpecialityID)
    {
        Optional<YearSpeciality> yearSpecialityOptional = this.yearSpecialityRepository.findById(yearSpecialityID);
        if(yearSpecialityOptional.isEmpty()) {
            throw new YearSpecialityNotFoundException("No year speciality with id " + yearSpecialityID + " was found");
        }
        return yearSpecialityOptional.get();
    }

    private Student getStudent(Integer studentID)
    {
        Optional<Student> studentOptional = this.studentRepository.findById(studentID);
        if(studentOptional.isEmpty()) {
            throw new StudentNotFoundException("No student with id " + studentID + " was found");
        }
        return studentOptional.get();
    }

    private void validateStudentEnrollment(Student student, YearSpeciality yearSpeciality) {
        // Check if the students is enrolled in less than 2 groups
        if(student.getGroups().size() >= 2) {
            throw new EnrollmentServiceException("Student with id: " + student.getsId() + " is already enrolled in 2 years!");
        }
        // Check if the student is enrolled in the current speciality
        if(student.getGroups().stream().map(Group::getyId).anyMatch(group_year -> group_year.equals(yearSpeciality))) {
            throw new EnrollmentServiceException("Student with id: " + student.getsId() + " is already enrolled in the selected speciality (id = " + yearSpeciality.getyId() +  ")!");
        }
    }
}
