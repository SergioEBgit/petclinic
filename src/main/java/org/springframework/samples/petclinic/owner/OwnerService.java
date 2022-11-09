package org.springframework.samples.petclinic.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OwnerService {

	@Autowired
	private OwnerRepository ownerRepository;

	public Owner findById(Integer id) {
		return ownerRepository.findById(id);
	}

	public Page<Owner> findByLastName(String lastname, Pageable pageable) {
		return this.ownerRepository.findByLastName(lastname, pageable);
	}

	public Page<Owner> findAll(int page, int pageSize) {
		return this.ownerRepository.findAll(PageRequest.of(page, pageSize));
	}

	public Owner save(Owner owner) {
		return this.ownerRepository.save(owner);
	}

}
