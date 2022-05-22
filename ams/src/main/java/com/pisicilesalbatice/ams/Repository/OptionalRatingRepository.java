package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.OptionalRating;
import com.pisicilesalbatice.ams.Model.OptionalRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionalRatingRepository extends JpaRepository<OptionalRating, OptionalRatingKey>
{
}