package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.Enrollment;
import com.pisicilesalbatice.ams.Repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService
{
    private final GradeRepository gradeRepository;

    @Autowired
    public GradeService(GradeRepository repository)
    {
        this.gradeRepository = repository;
    }

    public List<Enrollment> getGrades() {
        return gradeRepository.findAll();
    }

    public void addGrade(Enrollment enrollment){
        gradeRepository.save(enrollment);
    }
}
