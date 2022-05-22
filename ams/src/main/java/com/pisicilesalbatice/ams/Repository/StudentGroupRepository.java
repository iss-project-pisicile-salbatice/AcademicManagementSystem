package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentGroupRepository extends JpaRepository<Group, Integer>
{
}