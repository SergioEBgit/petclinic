package org.springframework.samples.petclinic.bill.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.visit.dto.Visit;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
public class Bill extends BaseEntity {

//	@OneToOne(fetch = FetchType.LAZY,mappedBy = "bill")
//	private Visit visit;

	@DecimalMin(value = "0.0")
	@Digits(integer = 3, fraction = 2)
	private Double money;

	@Column(name = "bill_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

//	public Visit getVisit() {
//		return visit;
//	}
//
//	public void setVisit(Visit visit) {
//		this.visit = visit;
//	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString(){
//		return this.getId() + "," + this.getVisit().getId() + "," + this.getMoney()  + (this.getDate()!=null?","+this.getDate().toString():"");
		return this.getId() +  "," + this.getMoney()  + (this.getDate()!=null?","+this.getDate().toString():"");
	}
}
