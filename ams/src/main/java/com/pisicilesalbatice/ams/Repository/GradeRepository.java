package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.Enrollment;
import com.pisicilesalbatice.ams.Model.EnrollmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Enrollment, EnrollmentKey>
{
}