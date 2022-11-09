package org.springframework.samples.petclinic.vet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface SpecialtyRepository extends Repository<Specialty, Integer> {

	void save(Specialty specialty);

	@Transactional(readOnly = true)
	@Query("SELECT specialty FROM Specialty specialty WHERE specialty.id =:id")
	Specialty findOne(@Param("id") Integer id);

}
