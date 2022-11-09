package org.springframework.samples.petclinic.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.OwnerService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
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


}
