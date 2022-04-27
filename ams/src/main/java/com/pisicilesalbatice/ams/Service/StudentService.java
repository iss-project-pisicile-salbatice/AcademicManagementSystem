package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.Course;
import com.pisicilesalbatice.ams.Model.Enrollment;
import com.pisicilesalbatice.ams.Model.Student;
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

    public Set<Enrollment> findGradesById(int id) {
        var optional = studentRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get().getEnrollments();
        } else {
            throw new RuntimeException(String.valueOf(id));
        }
    }

    public Set<Course> getStudentCourses(int sId) {
        var optional = studentRepository.findById(sId);
        if (optional.isPresent()) {
            Set<Enrollment> enrollments = optional.get().getEnrollments();
            return enrollments.stream().map(Enrollment::getCourse).collect(Collectors.toSet());
        } else {
            throw new RuntimeException("No student with id " + sId);
        }
    }

//    public Optional<Set<Grade>> findGradesById(int id) {
//        return Optional.ofNullable(studentRepository.findById(id).get().getGrades());
////        return studentRepository.findById(id).get().getGrades();
////
////        return studentRepository.findById(id)
////                .map(Student::getGrades)
////                .orElseThrow(() -> new RuntimeException(String.valueOf(id)));
//    }
}
