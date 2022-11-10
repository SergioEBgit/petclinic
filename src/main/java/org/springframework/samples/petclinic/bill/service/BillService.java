package org.springframework.samples.petclinic.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.samples.petclinic.bill.dao.BillRepository;
import org.springframework.samples.petclinic.bill.dto.Bill;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BillService {

	@Autowired
	private BillRepository billRepository;

	public Bill findById(Integer id){
		Optional<Bill> tmp = this.billRepository.findById(id);
		return (tmp.isPresent()? tmp.get() : null);
	}

	public Bill save(Bill bill){
		return this.billRepository.save(bill);
	}

	public List<Bill> findAll(int page, int pageSize){
		Pageable pageable = PageRequest.of(page,pageSize);
		Page<Bill> tmp = this.billRepository.findAll(pageable);
		return tmp.getContent();
	}

	public List<Bill> findAllPaidBills(int page, int pageSize){
		Pageable pageable = PageRequest.of(page,pageSize);
		Page<Bill> tmp = this.billRepository.findAllPaidBills(pageable);
		return tmp.getContent();
	}

	public List<Bill> findAllUnpaidBills(int page, int pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize);
		Page<Bill> tmp = this.billRepository.findAllUnpaidBills(pageable);
		return tmp.getContent();
	}
}
