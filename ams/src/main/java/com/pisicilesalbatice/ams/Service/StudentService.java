package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.Student;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void addStudent(Student student){
        studentRepository.save(student);
    }
}
