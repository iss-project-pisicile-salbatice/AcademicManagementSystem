package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.Enrollment;
import com.pisicilesalbatice.ams.Model.EnrollmentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentKey>
{
}