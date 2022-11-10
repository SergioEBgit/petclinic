package org.springframework.samples.petclinic.visit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.visit.dto.Visit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface VisitRepository extends Repository<Visit,Integer> {

	@Transactional(readOnly = true)
	Optional<Visit> findById(@Param("id") Integer id);

	@Transactional(readOnly = true)
	Visit save(Visit visit);

	@Query("SELECT visit FROM Visit visit")
	@Transactional(readOnly = true)
	Page<Visit> findAll(Pageable pageable);
}
