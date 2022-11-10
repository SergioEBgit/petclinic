package org.springframework.samples.petclinic.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.bill.dto.Bill;
import org.springframework.samples.petclinic.bill.service.BillService;
import org.springframework.samples.petclinic.visit.dto.Visit;
import org.springframework.samples.petclinic.visit.service.VisitService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/bills")
public class RestBillController {

	@Autowired
	private BillService billService;

	@Autowired
	private VisitService visitService;

	@RequestMapping(method = RequestMethod.GET,params = {"filter"})
	@ResponseBody
	public List<Bill> getBills(@RequestParam(value="filter",defaultValue = "all") String filter){
		int page = 0;
		int pageSize = 10;
		List<Bill> bills = new ArrayList<>();
		switch (filter.trim().toLowerCase()){
		case "all":
			bills = this.billService.findAll(page,pageSize);
			break;
		case "pagadas":
			bills = this.billService.findAllPaidBills(page,pageSize);
			break;
		case "no_pagadas":
			bills = this.billService.findAllUnpaidBills(page,pageSize);
			break;
		}
		return bills;
	}

	@RequestMapping(method = RequestMethod.GET,value="/{idBill}/visit/{idVisit}")
	public Visit getVisitDetails(@PathVariable(value="idBill")Integer idBill,
								 @PathVariable(value="idVisit")Integer idVisit){
		Visit visit = this.visitService.findById(idVisit);
		Bill bill = null;
		if (visit != null)
			bill = visit.getBill();
		if ((bill != null) && (bill.getId() == idBill))
			return visit;
		else
			return null;
	}

	@RequestMapping(method = RequestMethod.POST,value="/{idBill}/visit/{idVisit}")
	public Visit addBillToVisit(@PathVariable(value="idBill")Integer idBill,
								 @PathVariable(value="idVisit")Integer idVisit){
		Visit visit = this.visitService.findById(idVisit);
		Bill bill = this.billService.findById(idBill);
		if ((visit != null) && (bill != null))
			visit.setBill(bill);
		visit = this.visitService.save(visit);
		return visit;
	}

}
