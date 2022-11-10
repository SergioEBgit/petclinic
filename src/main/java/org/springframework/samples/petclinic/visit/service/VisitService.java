package org.springframework.samples.petclinic.visit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.visit.dao.VisitRepository;
import org.springframework.samples.petclinic.visit.dto.Visit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitService {

	@Autowired
	private VisitRepository visitRepository;

	public Visit findById(Integer id){
		Optional<Visit> tmp = this.visitRepository.findById(id);
		return (tmp.isPresent()? tmp.get() : null);
	}

	public Visit save(Visit visit){
		return this.visitRepository.save(visit);
	}

	public List<Visit> findAll(int page, int pageSize){
		Pageable pageable = PageRequest.of(page,pageSize);
		Page<Visit> list = this.visitRepository.findAll(pageable);
		return list.getContent();
	}
}
