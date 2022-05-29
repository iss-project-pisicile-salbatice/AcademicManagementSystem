package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Exceptions.Exceptions.StudentNotFoundException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.YearSpecialityNotFoundException;
import com.pisicilesalbatice.ams.Model.*;
import com.pisicilesalbatice.ams.Model.DTO.BasicDiscipline;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.studentRepository = repository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(String contract, Date date) {
        Student student = new Student(date, contract);
        return studentRepository.save(student);
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.valueOf(id)));
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }

    public Student replaceStudent(Student newStudent, int id) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setContract(student.getContract());
                    student.setEnrollmentDate(student.getEnrollmentDate());
                    return studentRepository.save(student);
                })
                .orElseGet(() -> {
                    newStudent.setsId(id);
                    return studentRepository.save(newStudent);
                });
    }

    public Set<Enrollment> getStudentEnrollments(int studentID) {
        Student student = getStudent(studentID);
        return student.getEnrollments();
    }

    public List<Course> getStudentCourses(int studentID) {
        return this.getStudentEnrollments(studentID).stream().map(Enrollment::getCourse).collect(Collectors.toList());
    }

    public List<YearSpeciality> getYearSpecialities(Integer id)
    {
        Student student = getStudent(id);
        // Return the year specialities of the groups in which the current student is present
        return student.getGroups().stream().map(Group::getyId).collect(Collectors.toList());
    }

    private Student getStudent(Integer studentID)
    {
        Optional<Student> studentOptional = this.studentRepository.findById(studentID);
        if(studentOptional.isEmpty()) {
            throw new StudentNotFoundException("No student with id " + studentID + " was found");
        }
        return studentOptional.get();
    }

    public Integer findStudentByUser(User user)
    {
        Optional<Student> optionalStudent = this.studentRepository.findByMainUser(user);
        if(optionalStudent.isEmpty())
        {
            return null;
        }
        return optionalStudent.get().getsId();
    }
}
