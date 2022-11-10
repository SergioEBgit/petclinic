package org.springframework.samples.petclinic.bill.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.bill.dto.Bill;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BillRepository extends Repository<Bill, Integer> {

	@Transactional(readOnly = true)
	Optional<Bill> findById(@Param("id") Integer id);

	@Transactional(readOnly = true)
	Bill save(Bill bill);

	@Query("SELECT bill FROM Bill bill")
	@Transactional(readOnly = true)
	Page<Bill> findAll(Pageable pageable);

	@Query("SELECT bill FROM Bill bill WHERE bill.date IS NOT NULL")
	@Transactional(readOnly = true)
	Page<Bill> findAllPaidBills(Pageable pageable);

	@Query("SELECT bill FROM Bill bill WHERE bill.date IS NULL")
	@Transactional(readOnly = true)
	Page<Bill> findAllUnpaidBills(Pageable pageable);
}
