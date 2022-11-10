package org.springframework.samples.petclinic.visit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.visit.dto.Visit;
import org.springframework.samples.petclinic.visit.service.VisitService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/visits")
public class RestVisitController {

	@Autowired
	private VisitService visitService;

	@RequestMapping(method = RequestMethod.GET,params = {"filter"})
	public List<Visit> getVisits(@RequestParam(value="filter",defaultValue = "all") String filter){
		int page = 0;
		int pageSize = 100;
		List<Visit> visits = this.visitService.findAll(page,pageSize);
		Predicate<Visit> p = null;
		switch (filter.trim().toLowerCase()){
			case "all":
				p = (v)->true;
				break;
			case "pagadas":
				p = (v)->{return (v.getBill().getDate() != null);};
				break;
			case "no_pagadas":
				p = (v)->{return (v.getBill().getDate() == null);};
				break;
		}
		visits = visits.stream().filter(p).collect(Collectors.toList());
		return visits;
	}

}
