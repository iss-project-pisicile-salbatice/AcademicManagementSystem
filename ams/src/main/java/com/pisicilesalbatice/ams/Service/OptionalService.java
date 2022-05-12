package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Model.*;
import com.pisicilesalbatice.ams.Repository.OptionalRatingRepository;
import com.pisicilesalbatice.ams.Repository.ProposedOptionalRepository;
import com.pisicilesalbatice.ams.Repository.StudentRepository;
import com.pisicilesalbatice.ams.Repository.YearSpecialityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
        // todo: error handling for missing year id or student id
        YearSpeciality year = this.yearSpecialityRepository.findById(year_id).get();

        return year.getProposedOptionals().stream().toList();
    }

    public void setOptionalRatings(Integer studentID, Integer yearSpecialityID, List<Pair<Integer, Integer>> ratings)
    {
        // Insert the given optional courses ratings into the optional rating table
        // param studentID: ID of student
        // param yearSpecialityID: ID of the speciality (used for validation)
        // param list: list of <ProposedOptionalID, Rating> which will be used in the insertion

        // todo: check if the ids are valid
        Student student = studentRepository.findById(studentID).get();
        YearSpeciality yearSpeciality = yearSpecialityRepository.findById(yearSpecialityID).get();
        Set<Integer> optionalIDs = yearSpeciality.getProposedOptionals().stream().map(ProposedOptional::getOptionalId).collect(Collectors.toSet());

        // check if the optionals belong to the year speciality
        ratings.forEach(pair -> {
            if(!optionalIDs.contains(pair.getLeft())) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Rating with ID " + pair.getLeft() + " does not belong to the year speciality with ID " + yearSpecialityID);
            }
        });

        // check if the user send the ratings to all the optionals for that year speciality
        Set<Integer> ratingOptionalIDs = ratings.stream().map(Pair::getLeft).collect(Collectors.toSet());
        if(!optionalIDs.containsAll(ratingOptionalIDs) || ratingOptionalIDs.containsAll(optionalIDs)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ratings from student " + studentID + " are incomplete!");
        }

        // Check if the ordering is correct (it is a set consisting of 1...len_ordering)
        Set<Integer> initialOrder = IntStream.range(1, ratings.size() + 1).boxed().collect(Collectors.toSet());
        Set<Integer> receivedOrder = ratings.stream().map(Pair::getRight).collect(Collectors.toSet());
        System.out.println(initialOrder);
        System.out.println(receivedOrder);
        if(!initialOrder.containsAll(receivedOrder) || !receivedOrder.containsAll(initialOrder)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ratings are incorrect");
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
}
