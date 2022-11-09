package org.springframework.samples.petclinic.pet.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.pet.dto.Pet;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PetRepository extends Repository<Pet,Integer> {

	@Transactional(readOnly = true)
	Optional<Pet> findById(Integer id);

	@Transactional(readOnly = true)
	@Query("SELECT pet FROM Pet pet WHERE name=:name")
	Optional<Pet> findByName(@Param("name")String name);

//	@Transactional(readOnly = true)
//	@Query("SELECT pet FROM Pet pet WHERE pet.birthdate BETWEEN :fromDate AND :toDate")
//	List<Pet> findByBirthDateOrderByBirthDateAsc(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
