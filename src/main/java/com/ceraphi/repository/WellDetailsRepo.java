package com.ceraphi.repository;

import com.ceraphi.entities.WellDetails;
import com.ceraphi.entities.WellInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WellDetailsRepo extends JpaRepository<WellDetails,Long> {
    Optional<WellDetails> findByWellInformationId(long id);
}
