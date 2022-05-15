package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.Grade;
import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService
{
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository repository)
    {
        this.studentRepository = repository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student){
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

    public Set<Grade> findGradesById(int id) {
        var optional = studentRepository.findById(id);
        if(optional.isPresent()){
            return optional.get().getGrades();
        }
        else {
            throw new RuntimeException(String.valueOf(id));
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
