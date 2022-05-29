package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.ERole;
import com.pisicilesalbatice.ams.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>
{
    Optional<Role> findByName(ERole name);
}