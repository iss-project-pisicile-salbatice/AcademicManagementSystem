package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.Grade;
import com.pisicilesalbatice.ams.Model.GradeKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, GradeKey>
{
}