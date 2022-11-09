package org.springframework.samples.petclinic.vet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.vet.dao.VetRepository;
import org.springframework.samples.petclinic.vet.dto.Vet;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class VetService {

	@Autowired
	private VetRepository vetRepository;

	public Collection<Vet> findAll() {
		return this.vetRepository.findAll();
	}

	public Page<Vet> findAll(Pageable pageable) {
		return this.vetRepository.findAll(pageable);
	}

	public Vet findOne(Integer id) {
		return this.vetRepository.findOne(id);
	}

	public Vet findById(Integer id) {
		return this.vetRepository.findById(id);
	}

	public Page<Vet> findRadiologists(Pageable pageable) {
		return this.vetRepository.findRadiologists(pageable);
	}

	public List<Vet> findBySpecialtyName(String name) {
		return this.vetRepository.findBySpecialtyName(name);
	}

	public Vet save(Vet vet) {
		return this.vetRepository.save(vet);
	}

}
