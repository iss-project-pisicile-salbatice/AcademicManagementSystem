package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.YearSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearSpecialityRepository extends JpaRepository<YearSpeciality, Integer>
{
}