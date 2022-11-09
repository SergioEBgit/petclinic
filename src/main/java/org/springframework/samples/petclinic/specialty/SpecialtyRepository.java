package org.springframework.samples.petclinic.specialty;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SpecialtyRepository extends Repository<Specialty, Integer> {

	void save(Specialty specialty);

	@Transactional(readOnly = true)
	@Query("SELECT specialty FROM Specialty specialty WHERE specialty.id =:id")
	Specialty findOne(@Param("id") Integer id);

	@Transactional(readOnly = true)
	Specialty findById(Integer id);

	@Transactional(readOnly = true)
	List<Specialty> findAll();
}
