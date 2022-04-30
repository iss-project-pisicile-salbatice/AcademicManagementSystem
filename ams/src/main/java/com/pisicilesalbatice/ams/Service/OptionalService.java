package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.ProposedOptional;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Repository.ProposedOptionalRepository;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionalService
{
    private final StudentRepository studentRepository;
    private final YearSpecialityRepository yearSpecialityRepository;
    private final ProposedOptionalRepository proposedOptionalRepository;

    public OptionalService(StudentRepository studentRepository, YearSpecialityRepository yearSpecialityRepository, ProposedOptionalRepository proposedOptionalRepository)
    {
        this.studentRepository = studentRepository;
        this.yearSpecialityRepository = yearSpecialityRepository;
        this.proposedOptionalRepository = proposedOptionalRepository;
    }

    public List<ProposedOptional> getProposedOptionals(Integer year_id)
    {
        // todo: error handling for missing year id or student id
        YearSpeciality year = this.yearSpecialityRepository.findById(year_id).get();

        return year.getProposedOptionals().stream().toList();
    }
}
