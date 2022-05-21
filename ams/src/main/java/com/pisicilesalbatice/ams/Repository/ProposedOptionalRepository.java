package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.ProposedOptional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposedOptionalRepository extends JpaRepository<ProposedOptional, Integer>
{
}