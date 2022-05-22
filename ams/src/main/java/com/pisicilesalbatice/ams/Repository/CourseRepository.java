package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer>
{
}
