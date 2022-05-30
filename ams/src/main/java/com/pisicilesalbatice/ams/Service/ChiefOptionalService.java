package com.pisicilesalbatice.ams.Service;

import com.pisicilesalbatice.ams.Exceptions.Exceptions.ChiefServiceException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.OptionalNotFoundException;
import com.pisicilesalbatice.ams.Exceptions.Exceptions.YearSpecialityNotFoundException;
import com.pisicilesalbatice.ams.Model.*;
import com.pisicilesalbatice.ams.Model.DTO.BasicAcceptedOptional;
import com.pisicilesalbatice.ams.Repository.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ChiefOptionalService
{
    private final ProposedOptionalRepository proposedOptionalRepository;
    private final AcceptedOptionalRepository acceptedOptionalRepository;
    private final CourseRepository courseRepository;
    private final YearSpecialityRepository yearSpecialityRepository;
    private final OptionalRatingRepository optionalRatingRepository;
    private final EnrollmentRepository enrollmentRepository;

    public ChiefOptionalService(ProposedOptionalRepository proposedOptionalRepository,
                                AcceptedOptionalRepository acceptedOptionalRepository,
                                CourseRepository courseRepository, YearSpecialityRepository yearSpecialityRepository, OptionalRatingRepository optionalRatingRepository, EnrollmentRepository enrollmentRepository)
    {
        this.proposedOptionalRepository = proposedOptionalRepository;
        this.courseRepository = courseRepository;
        this.acceptedOptionalRepository = acceptedOptionalRepository;
        this.yearSpecialityRepository = yearSpecialityRepository;
        this.optionalRatingRepository = optionalRatingRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<ProposedOptional> getProposedOptionals()
    {
        return proposedOptionalRepository.findAll();
    }

    public List<ProposedOptional> getProposedOptionalsOfYear(Integer yearId)
    {
        YearSpeciality yearSpeciality = getYearSpeciality(yearId);
        var allList = this.getProposedOptionals();
        return allList.stream().filter(optional -> optional.getYearSpeciality().equals(yearSpeciality)).collect(Collectors.toList());
    }

    public AcceptedOptional acceptOptional(Integer oId)
    {
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

    public AcceptedOptional setMaximumStudents(Integer cId, Integer maximumStudents)
    {
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
        if (lessThan20Optionals.size() > 0)
        {
            throw new ChiefServiceException("There cannot be courses with less than 20 students!");
        }

        // Check if the sum of the maximum is enough
        YearSpeciality yearSpeciality = this.getYearSpeciality(yearId);
        Integer totalStudentsPerYear = yearSpeciality.getStudentGroup().stream()
                .map(group -> group.getStudents().size()).reduce(0, Integer::sum);
        Integer totalMaximumStudents = optionalsMaximumList.stream()
                .map(Pair::getRight).reduce(0, Integer::sum);
        if (totalMaximumStudents < 1.25 * totalStudentsPerYear)
        {
            throw new ChiefServiceException("There are not enough spaces for all students. Increase the maximum capacities!");
        }

        // Update the maximum capacities
        optionalsMaximumList.forEach(pair -> this.setMaximumStudents(pair.getLeft(), pair.getRight()));
    }

    @Transactional
    public void assignOptionals(Integer year_id)
    {
        YearSpeciality yearSpeciality = getYearSpeciality(year_id);
        List<Enrollment> tempEnrollments = new ArrayList<>();

        // Get the ratings ordered by preference and time
        List<OptionalRating> optionalRatingsAll = this.optionalRatingRepository.findByOrderByRatingAscReceivedDateAscReceivedTimeAsc();
        // (I know this could have been done directly in db, but it still works)
        List<OptionalRating> optionalRatings = optionalRatingsAll.stream().filter(rating -> rating.getAcceptedOptional().getYear().equals(yearSpeciality)).collect(Collectors.toList());

        // Assign the students to the optionals
        assignStudentsToPreferredOptionals(tempEnrollments, optionalRatings);

        // Remove accepted courses with less than 20 students
        List<AcceptedOptional> badOptionals = this.acceptedOptionalRepository.findAll().stream().filter(optional -> optional.getEnrollments().size() < 20).collect(Collectors.toList());
        List<Student> removedStudents = new ArrayList<>();
        badOptionals.forEach(optional -> removedStudents.addAll(optional.getEnrollments().stream().map(Enrollment::getStudent).collect(Collectors.toList())));

        // If there were any optionals removed, reassign the students to the remaining courses
        if(!badOptionals.isEmpty())
        {
            // Remove the enrollments from the bad optionals
            tempEnrollments = tempEnrollments.stream().filter(enrollment -> !badOptionals.contains((AcceptedOptional) enrollment.getCourse())).collect(Collectors.toList());

            // Get again all the optionals ratings which belong to removed students and which do not contain the removed optional
            optionalRatings = optionalRatingsAll.stream()
                    .filter(rating -> rating.getAcceptedOptional().getYear().equals(yearSpeciality))
                    .filter(rating -> !badOptionals.contains(rating.getAcceptedOptional()))
                    .filter(rating -> removedStudents.contains(rating.getStudent()))
                    .collect(Collectors.toList());
            // Run the matching again
            assignStudentsToPreferredOptionals(tempEnrollments, optionalRatings);
        }

        // Check if there is any student from the year speciality which is not enrolled in an accepted optional
        Set<Student> allStudents = new HashSet<>();
        yearSpeciality.getStudentGroup().forEach(group -> allStudents.addAll(group.getStudents()));

        // Find all the students which will be enrolled in an optional
        Set<Student> futureEnrolledStudents = tempEnrollments.stream().map(Enrollment::getStudent).collect(Collectors.toSet());

        if (!allStudents.equals(futureEnrolledStudents))
        {
            throw new ChiefServiceException("Error: Cannot assign all students to the optional. Check again the maximum capacity for each optional");
        }

        // Save the enrollments
        this.enrollmentRepository.saveAll(tempEnrollments);
    }

    private void assignStudentsToPreferredOptionals(List<Enrollment> tempEnrollments, List<OptionalRating> optionalRatings)
    {
        // The logic here:
        // You keep getting each rating like a stack (the top of the sorted list is the rating with the highest priority)
        // (priority meaning the earliest student to set the lowest ranking)
        // If the student can be enrolled in that course (the maximum number of students is not reached yet), that
        //  student gets enrolled to that class and all their entries are deleted
        // Else, the student will have another option with their next rating
        while (!optionalRatings.isEmpty())
        {
            // Get the student and optional
            OptionalRating currentRating = optionalRatings.get(0);
            optionalRatings.remove(0);
            Student currentStudent = currentRating.getStudent();
            AcceptedOptional acceptedOptional = currentRating.getAcceptedOptional();

            int enrolledStudents = acceptedOptional.getEnrollments().size();
            // Check if the student can be assigned to that optional
            if (enrolledStudents < acceptedOptional.getMaximumStudents())
            {
                // Assign the student to the optional
                Enrollment newEnrolledment = new Enrollment(currentStudent, acceptedOptional);
                acceptedOptional.getEnrollments().add(newEnrolledment);
                tempEnrollments.add(newEnrolledment);
                // Remove the student entries from the list
                optionalRatings.removeIf(optionalRating -> optionalRating.getStudent().equals(currentStudent));
            }
        }
    }

    public List<AcceptedOptional> getAcceptedOptionals(Integer year_id)
    {
        YearSpeciality yearSpeciality = getYearSpeciality(year_id);
        return this.acceptedOptionalRepository.findAllByYear(yearSpeciality);
    }

    private ProposedOptional getProposedOptional(Integer optionalId)
    {
        Optional<ProposedOptional> proposedOptional = this.proposedOptionalRepository.findById(optionalId);
        if (proposedOptional.isEmpty())
        {
            throw new OptionalNotFoundException("No proposed optional with id " + optionalId + " was found");
        }
        return proposedOptional.get();
    }

    private AcceptedOptional getAcceptedOptional(Integer optionalId)
    {
        Optional<AcceptedOptional> acceptedOptional = this.acceptedOptionalRepository.findById(optionalId);
        if (acceptedOptional.isEmpty())
        {
            throw new OptionalNotFoundException("No accepted optional with id " + optionalId + " was found");
        }
        return acceptedOptional.get();
    }

    private YearSpeciality getYearSpeciality(Integer yearSpecialityID)
    {
        Optional<YearSpeciality> yearSpecialityOptional = this.yearSpecialityRepository.findById(yearSpecialityID);
        if (yearSpecialityOptional.isEmpty())
        {
            throw new YearSpecialityNotFoundException("No year speciality with id " + yearSpecialityID + " was found");
        }
        return yearSpecialityOptional.get();
    }
}
