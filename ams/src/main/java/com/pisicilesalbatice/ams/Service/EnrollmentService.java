package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Model.StudentGroup;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Repository.StudentGroupRepository;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
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

    public void enrollStudent(Integer studentID, Integer yearSpeciality, Date enrollDate)
    {
        // Check if the students is enrolled in less than 2 groups


        // todo: error handling for missing year id or student id
        YearSpeciality year = this.yearSpecialityRepository.findById(yearSpeciality).get();
        Set<StudentGroup> groups = year.getStudentGroup();
        // cap: 25
        Student student = this.studentRepository.findById(studentID).get();

        var studentGroup = groups.stream().filter(group -> group.getStudents().size() < MAX_GROUP_SIZE).findFirst();
        if(studentGroup.isPresent()) {
            student.getGroups().add(studentGroup.get());
            //studentGroup.get().getStudents().add(student);
        } else {
            int lastGroupName = groups.stream()
                    .mapToInt(group -> Integer.parseInt(group.getGroupName()))
                    .max().orElse(year.getSpecialityHash() * 100 + year.getYear() * 10);
            var newGroup = new StudentGroup(String.valueOf(lastGroupName + 1), year);
            this.studentGroupRepository.save(newGroup);
            student.getGroups().add(newGroup);
            //newGroup.getStudents().add(student);
        }

        student.setEnrollmentDate(enrollDate);
        this.studentRepository.save(student);
    }
}
