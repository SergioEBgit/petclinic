package org.springframework.samples.petclinic.specialty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.specialty.dao.SpecialtyRepository;
import org.springframework.samples.petclinic.specialty.dto.Specialty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

	@Autowired
	private SpecialtyRepository specialtyRepository;

	public Specialty findById(Integer id) {
		return this.specialtyRepository.findById(id);
	}

	public Specialty findOne(Integer id) {
		return this.specialtyRepository.findOne(id);
	}

	public List<Specialty> findAll() {
		return this.specialtyRepository.findAll();
	}

}
