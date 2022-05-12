package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.OptionalRating;
import com.pisicilesalbatice.ams.Model.OptionalRatingKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionalRatingRepository extends JpaRepository<OptionalRating, OptionalRatingKey>
{
}