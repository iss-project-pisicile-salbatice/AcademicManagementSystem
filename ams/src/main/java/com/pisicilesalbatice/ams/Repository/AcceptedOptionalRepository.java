package com.pisicilesalbatice.ams.Repository;

import com.pisicilesalbatice.ams.Model.AcceptedOptional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcceptedOptionalRepository extends JpaRepository<AcceptedOptional, Integer> {
}