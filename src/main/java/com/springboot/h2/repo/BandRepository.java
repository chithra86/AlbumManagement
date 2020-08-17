package com.springboot.h2.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.h2.model.Band;

@Repository
public interface BandRepository extends JpaRepository<Band,Long> {

	Optional<Band> findById(Long bandId);
	

}
