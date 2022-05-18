package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.*;
import com.pisicilesalbatice.ams.Model.DTO.BasicDiscipline;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
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
        var optional = studentRepository.findById(studentID);
        if (optional.isPresent()) {
            return optional.get().getEnrollments();
        } else {
            throw new RuntimeException("No student with id " + studentID);
        }
    }

    public List<Course> getStudentCourses(int studentID) {
        return this.getStudentEnrollments(studentID).stream().map(Enrollment::getCourse).collect(Collectors.toList());
    }

    public List<YearSpeciality> getYearSpecialities(Integer id)
    {
        // todo: validate if the id is valid
        Student student = studentRepository.findById(id).get();
        return student.getGroups().stream().map(Group::getyId).collect(Collectors.toList());
    }
}
