package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.AcceptedOptional;
import com.pisicilesalbatice.ams.Model.ProposedOptional;
import com.pisicilesalbatice.ams.Repository.AcceptedOptionalRepository;
import com.pisicilesalbatice.ams.Repository.CourseRepository;
import com.pisicilesalbatice.ams.Repository.ProposedOptionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChiefOptionalService {
    private final ProposedOptionalRepository proposedOptionalRepository;
    private final AcceptedOptionalRepository acceptedOptionalRepository;
    private final CourseRepository courseRepository;

    public ChiefOptionalService(ProposedOptionalRepository proposedOptionalRepository,
                                AcceptedOptionalRepository acceptedOptionalRepository,
                                CourseRepository courseRepository) {
        this.proposedOptionalRepository = proposedOptionalRepository;
        this.courseRepository = courseRepository;
        this.acceptedOptionalRepository = acceptedOptionalRepository;
    }

    public List<ProposedOptional> getProposedOptionals() {
        return proposedOptionalRepository.findAll();
    }

    public AcceptedOptional acceptOptional(Integer oId) {
        ProposedOptional proposedOptional = proposedOptionalRepository.getById(oId);
        AcceptedOptional acceptedOptional = new AcceptedOptional(proposedOptional.getOptionalName(),
                proposedOptional.getTeacher(), proposedOptional.getYearSpeciality(), null);
        //maximumStudents -> I assume the chief will accept optionals and will put that number in afterwards.
        //courseRepository.save(acceptedOptional);
        //^ this saves the now-accepted optional into the course table
        // it is not needed because JPA is smart af and does exactly this by itself
        proposedOptionalRepository.delete(proposedOptional);
        //^ my gut tells me i should delete the now-accepted optional from the other table
        return acceptedOptionalRepository.save(acceptedOptional);
    }

    public AcceptedOptional setMaximumStudents(Integer cId, Integer maximumStudents) {
        AcceptedOptional acceptedOptional = acceptedOptionalRepository.getById(cId);
        acceptedOptional.setMaximumStudents(maximumStudents);
        return acceptedOptionalRepository.save(acceptedOptional);
    }
}
