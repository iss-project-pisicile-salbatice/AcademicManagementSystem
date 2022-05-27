package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.OptionalRating;
import com.pisicilesalbatice.ams.Model.OptionalRatingKey;
import com.pisicilesalbatice.ams.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionalRatingRepository extends JpaRepository<OptionalRating, OptionalRatingKey>
{
    List<OptionalRating> findByOrderByRatingAscReceivedDateAscReceivedTimeAsc();

    long deleteByStudent(Student student);
}