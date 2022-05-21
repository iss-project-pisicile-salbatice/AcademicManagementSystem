package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Exceptions.Exceptions.GradeNotFoundException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.GradeServiceException;
import com.pisicilesalbatice.ams.Model.Enrollment;
import com.pisicilesalbatice.ams.Model.EnrollmentKey;
import com.pisicilesalbatice.ams.Repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class GradeService
{
    private final EnrollmentRepository gradeRepository;

    @Autowired
    public GradeService(EnrollmentRepository repository)
    {
        this.gradeRepository = repository;
    }

    public List<Enrollment> getGrades() {
        return gradeRepository.findAll();
    }

    public void addGrade(Enrollment enrollment){
        gradeRepository.save(enrollment);
    }

    private void checkExistsGradeID(Integer studentID, Integer courseID)
    {
        EnrollmentKey enrollmentKey = new EnrollmentKey(studentID, courseID);
        if(gradeRepository.findById(enrollmentKey).isEmpty())
        {
            throw new GradeNotFoundException("Enrollment with studentID " + studentID + " and courseID " + courseID + " not found");
        }
    }

    public void gradeStudent(Integer studentID, Integer courseID, Integer gradeValue)
    {
        this.checkExistsGradeID(studentID, courseID);
        Enrollment enrollment =  gradeRepository.getById(new EnrollmentKey(studentID, courseID));
        this.gradeStudent(enrollment, gradeValue);
    }

    public void gradeStudent(Enrollment enrollment, Integer gradeValue)
    {
        // Validate the grade value
        if(gradeValue < 1 || gradeValue > 10)
        {
            throw new GradeServiceException("Grades can only be between 1 and 10!");
        }

        enrollment.setGrade(gradeValue);
        gradeRepository.save(enrollment);
    }
}
