package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentGroupRepository extends JpaRepository<Group, Integer>
{
}