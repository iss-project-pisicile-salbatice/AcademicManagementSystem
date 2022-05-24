package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Exceptions.Exceptions.ChiefServiceException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.OptionalNotFoundException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.YearSpecialityNotFoundException;
import com.pisicilesalbatice.ams.Model.AcceptedOptional;
import com.pisicilesalbatice.ams.Model.Pair;
import com.pisicilesalbatice.ams.Model.ProposedOptional;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import com.pisicilesalbatice.ams.Repository.AcceptedOptionalRepository;
import com.pisicilesalbatice.ams.Repository.CourseRepository;
import com.pisicilesalbatice.ams.Repository.ProposedOptionalRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChiefOptionalService {
    private final ProposedOptionalRepository proposedOptionalRepository;
    private final AcceptedOptionalRepository acceptedOptionalRepository;
    private final CourseRepository courseRepository;
    private final YearSpecialityRepository yearSpecialityRepository;

    public ChiefOptionalService(ProposedOptionalRepository proposedOptionalRepository,
                                AcceptedOptionalRepository acceptedOptionalRepository,
                                CourseRepository courseRepository, YearSpecialityRepository yearSpecialityRepository) {
        this.proposedOptionalRepository = proposedOptionalRepository;
        this.courseRepository = courseRepository;
        this.acceptedOptionalRepository = acceptedOptionalRepository;
        this.yearSpecialityRepository = yearSpecialityRepository;
    }

    public List<ProposedOptional> getProposedOptionals() {
        return proposedOptionalRepository.findAll();
    }

    public List<ProposedOptional> getProposedOptionalsOfYear(Integer yearId)
    {
        YearSpeciality yearSpeciality = getYearSpeciality(yearId);
        var allList = this.getProposedOptionals();
        return allList.stream().filter(optional -> optional.getYearSpeciality().equals(yearSpeciality)).collect(Collectors.toList());
    }

    public AcceptedOptional acceptOptional(Integer oId) {
        ProposedOptional proposedOptional = getProposedOptional(oId);
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
        AcceptedOptional acceptedOptional = getAcceptedOptional(cId);
        acceptedOptional.setMaximumStudents(maximumStudents);
        return acceptedOptionalRepository.save(acceptedOptional);
    }

    public void setMaximumStudentsAll(Integer yearId, List<Pair<Integer, Integer>> optionalsMaximumList)
    {
        // Tries to add the maximum number of students to every specified optional discipline
        // Fails if the values are too low for the number of enrolled students
        // param yearId: The id of the year speciality
        // param list: list of <AcceptedOptionalId, MaximumNumberOfStudents> used for this purpose

        // Check if there is any optional with a maximum less than 20 (restricted condition)
        var lessThan20Optionals = optionalsMaximumList.stream().filter(pair -> pair.getRight() < 20).collect(Collectors.toList());
        if(lessThan20Optionals.size() > 0)
        {
            throw new ChiefServiceException("There cannot be courses with less than 20 students!");
        }

        // Check if the sum of the maximum is enough
        YearSpeciality yearSpeciality = this.getYearSpeciality(yearId);
        Integer totalStudentsPerYear = yearSpeciality.getStudentGroup().stream()
                .map(group -> group.getStudents().size()).reduce(0, Integer::sum);
        Integer totalMaximumStudents = optionalsMaximumList.stream()
                .map(Pair::getRight).reduce(0, Integer::sum);
        if(totalMaximumStudents < 1.25 * totalStudentsPerYear)
        {
            throw new ChiefServiceException("There are not enough spaces for all students. Increase the maximum capacities!");
        }

        // Update the maximum capacities
        optionalsMaximumList.forEach(pair -> this.setMaximumStudents(pair.getLeft(), pair.getRight()));
    }

    private ProposedOptional getProposedOptional(Integer optionalId)
    {
        Optional<ProposedOptional> proposedOptional = this.proposedOptionalRepository.findById(optionalId);
        if(proposedOptional.isEmpty())
        {
            throw new OptionalNotFoundException("No proposed optional with id " + optionalId + " was found");
        }
        return proposedOptional.get();
    }

    private AcceptedOptional getAcceptedOptional(Integer optionalId)
    {
        Optional<AcceptedOptional> acceptedOptional = this.acceptedOptionalRepository.findById(optionalId);
        if(acceptedOptional.isEmpty())
        {
            throw new OptionalNotFoundException("No accepted optional with id " + optionalId + " was found");
        }
        return acceptedOptional.get();
    }

    private YearSpeciality getYearSpeciality(Integer yearSpecialityID)
    {
        Optional<YearSpeciality> yearSpecialityOptional = this.yearSpecialityRepository.findById(yearSpecialityID);
        if(yearSpecialityOptional.isEmpty()) {
            throw new YearSpecialityNotFoundException("No year speciality with id " + yearSpecialityID + " was found");
        }
        return yearSpecialityOptional.get();
    }
}
