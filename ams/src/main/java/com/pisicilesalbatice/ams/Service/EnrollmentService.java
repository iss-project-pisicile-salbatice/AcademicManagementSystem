package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Model.Group;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Repository.StudentGroupRepository;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.stereotype.Service;

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

    public EnrollmentService(StudentRepository studentRepository, StudentGroupRepository studentGroupRepository, YearSpecialityRepository yearSpecialityRepository)
    {
        this.studentRepository = studentRepository;
        this.studentGroupRepository = studentGroupRepository;
        this.yearSpecialityRepository = yearSpecialityRepository;
    }

    public List<YearSpeciality> getAllYears()
    {
        return this.yearSpecialityRepository.findAll();
    }

    public void enrollStudent(Integer studentID, Integer yearSpecialityID, Date enrollDate)
    {
        // todo: error handling for missing year id or student id

        Student student = this.studentRepository.findById(studentID).get();
        YearSpeciality year = this.yearSpecialityRepository.findById(yearSpecialityID).get();

        // Check if the students is enrolled in less than 2 groups
        if(student.getGroups().size() >= 2) {
            throw new RuntimeException("Student with id: " + studentID + " is already enrolled in 2 years!");
        }
        // Check if the student is enrolled in the current speciality
        if(student.getGroups().stream().map(Group::getyId).anyMatch(group_year -> group_year.equals(year))) {
            throw new RuntimeException("Student with id: " + studentID + " is already enrolled in the selected speciality!");
        }

        // Get the groups of the selected year
        Set<Group> groups = year.getStudentGroup();

        // Check if there are any groups with less than the maximum capacity
        Optional<Group> studentGroup = groups.stream().filter(group -> group.getStudents().size() < MAX_GROUP_SIZE).findFirst();
        if(studentGroup.isPresent()) {
            // Add the current student to the found group
            studentGroup.get().getStudents().add(student);
            student.getGroups().add(studentGroup.get());
        } else {
            // Create a new group and add the student to it
            int lastGroupName = groups.stream()
                    .mapToInt(group -> Integer.parseInt(group.getGroupName()))
                    .max().orElse(year.getSpecialityHash() * 100 + year.getYear() * 10);
            Group newGroup = new Group(String.valueOf(lastGroupName + 1), year);
            this.studentGroupRepository.save(newGroup);
            student.getGroups().add(newGroup);
        }

        // Save the enrollment date and update the student in the repository
        student.setEnrollmentDate(enrollDate);
        this.studentRepository.save(student);
    }
}
