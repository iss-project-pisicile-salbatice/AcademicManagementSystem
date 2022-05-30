package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.AcceptedOptional;
import com.pisicilesalbatice.ams.Model.YearSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptedOptionalRepository extends JpaRepository<AcceptedOptional, Integer> {
    List<AcceptedOptional> findAllByYear(YearSpeciality yearSpeciality);
}