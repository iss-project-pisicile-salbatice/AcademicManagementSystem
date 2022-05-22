package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Exceptions.Exceptions.OptionalServiceException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.StudentNotFoundException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.YearSpecialityNotFoundException;
import com.pisicilesalbatice.ams.Model.*;
import com.pisicilesalbatice.ams.Repository.OptionalRatingRepository;
import com.pisicilesalbatice.ams.Repository.ProposedOptionalRepository;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OptionalService
{
    private final StudentRepository studentRepository;
    private final YearSpecialityRepository yearSpecialityRepository;
    private final ProposedOptionalRepository proposedOptionalRepository;
    private final OptionalRatingRepository optionalRatingRepository;

    public OptionalService(StudentRepository studentRepository, YearSpecialityRepository yearSpecialityRepository, ProposedOptionalRepository proposedOptionalRepository, OptionalRatingRepository optionalRatingRepository)
    {
        this.studentRepository = studentRepository;
        this.yearSpecialityRepository = yearSpecialityRepository;
        this.proposedOptionalRepository = proposedOptionalRepository;
        this.optionalRatingRepository = optionalRatingRepository;
    }

    public List<ProposedOptional> getProposedOptionals(Integer year_id)
    {
        YearSpeciality year = getYearSpeciality(year_id);

        // Get the proposed optionals of the year speciality with the given id
        return year.getProposedOptionals().stream().toList();
    }

    public void setOptionalRatings(Integer studentID, Integer yearSpecialityID, List<Pair<Integer, Integer>> ratings)
    {
        // Insert the given optional courses ratings into the optional rating table
        // param studentID: ID of student
        // param yearSpecialityID: ID of the speciality (used for validation)
        // param list: list of <ProposedOptionalID, Rating> which will be used in the insertion

        Student student = getStudent(studentID);
        YearSpeciality yearSpeciality = getYearSpeciality(yearSpecialityID);
        Set<Integer> optionalIDs = yearSpeciality.getProposedOptionals().stream().map(ProposedOptional::getOptionalId).collect(Collectors.toSet());

        // check if the optionals belong to the year speciality
        ratings.forEach(pair -> {
            if(!optionalIDs.contains(pair.getLeft())) {
                throw new OptionalServiceException("Rating with ID " + pair.getLeft() + " does not belong to the year speciality with ID " + yearSpecialityID);
            }
        });

        // check if the user send the ratings to all the optionals for that year speciality
        Set<Integer> ratingOptionalIDs = ratings.stream().map(Pair::getLeft).collect(Collectors.toSet());
        System.out.println(ratingOptionalIDs);
        if(!optionalIDs.containsAll(ratingOptionalIDs) || !ratingOptionalIDs.containsAll(optionalIDs)) {
            throw new OptionalServiceException("Ratings from student " + studentID + " are incomplete!");
        }

        // Check if the ordering is correct (it is a set consisting of 1...len_ordering)
        Set<Integer> initialOrder = IntStream.range(1, ratings.size() + 1).boxed().collect(Collectors.toSet());
        Set<Integer> receivedOrder = ratings.stream().map(Pair::getRight).collect(Collectors.toSet());
        System.out.println(initialOrder);
        System.out.println(receivedOrder);
        if(!initialOrder.containsAll(receivedOrder) || !receivedOrder.containsAll(initialOrder)) {
            throw new OptionalServiceException("Ratings are incorrect");
        }

        // add the ratings to the database
        ratings.forEach(pair -> {
            Integer proposedOptionalID = pair.getLeft();
            Integer position = pair.getRight();
            ProposedOptional proposedOptional = proposedOptionalRepository.getById(proposedOptionalID);
            OptionalRating optionalRating = new OptionalRating(student, proposedOptional, position);
            optionalRatingRepository.save(optionalRating);
        });
    }

    private YearSpeciality getYearSpeciality(Integer yearSpecialityID)
    {
        Optional<YearSpeciality> yearSpecialityOptional = this.yearSpecialityRepository.findById(yearSpecialityID);
        if(yearSpecialityOptional.isEmpty()) {
            throw new YearSpecialityNotFoundException("No year speciality with id " + yearSpecialityID + " was found");
        }
        return yearSpecialityOptional.get();
    }

    private Student getStudent(Integer studentID)
    {
        Optional<Student> studentOptional = this.studentRepository.findById(studentID);
        if(studentOptional.isEmpty()) {
            throw new StudentNotFoundException("No student with id " + studentID + " was found");
        }
        return studentOptional.get();
    }
}
