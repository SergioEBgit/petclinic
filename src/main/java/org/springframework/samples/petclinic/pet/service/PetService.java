package org.springframework.samples.petclinic.pet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.dto.Owner;
import org.springframework.samples.petclinic.owner.service.OwnerService;
import org.springframework.samples.petclinic.pet.dao.PetRepository;
import org.springframework.samples.petclinic.pet.dto.Pet;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private OwnerService ownerService;

	public Pet findById(Integer id){
		Optional<Pet> opt = this.petRepository.findById(id);;
		Pet pet = opt.isPresent()? opt.get() : null;
		return pet;
	}

	public Pet findByPetName(String name){
		Optional<Pet> opt = this.petRepository.findByName(name);
		Pet pet = opt.isPresent()? opt.get() : null;
		return pet;
	}

	public List<Pet> findAllByOwner(Owner owner){
		Owner tmp = this.ownerService.findById(owner.getId());
		return tmp.getPets();
	}


	public List<Pet> findByBirthDate(Integer year){
		Date fromDate,toDate;

		try {
			fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-01-01");
			toDate = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-12-31");
		}catch(Exception e){
			e.printStackTrace();
		}
		LocalDate fecha = LocalDate.of(year.intValue(),11,30);
		List<Pet>pets=this.petRepository.findByBirthDateOrderByBirthDateAsc(fecha);
		return pets;
	}

//
//	public List<Pet> prueba(Integer year){
//		Date fromDate;
//		Date toDate;
//		try {
//			fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(year+"-01-01");
//			toDate = new SimpleDateFormat("yyyy-MM-dd").parse(year+"-12-31");
//		} catch (ParseException e) {
//			throw new RuntimeException(e);
//		}
//		return this.petRepository.findByBirthDateOrderByBirthDateAsc(fromDate,toDate);
//	}
}
