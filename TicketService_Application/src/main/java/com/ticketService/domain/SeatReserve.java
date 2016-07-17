package com.ticketService.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
public class SeatReserve {

	@Id
	private int seatReserveId;
	
	@Column(name="reservationCode")
	private String resevationCode;
	
	@Temporal(TemporalType.DATE)
	private Date reserveTime;
	
	
	
	@OneToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
	private Date holdTime;
	
	@Column(name="numSeats")
	private int numSeats;
	
	public SeatReserve(){
		
	}
	
	public SeatReserve(int seatReserveId,String resevationCode,Date reserveTime,Date holdTime,Customer customer,int numSeats) {
		this.seatReserveId = seatReserveId;
		this.resevationCode = resevationCode;
		this.reserveTime = reserveTime;
		//this.seatHold = seatHold;
		this.holdTime=holdTime;
		this.customer = customer;
		this.numSeats = numSeats;
	}


	public int getSeatReserveId() {
		return seatReserveId;
	}

	public void setSeatReserveId(int seatReserveId) {
		this.seatReserveId = seatReserveId;
	}

	public String getResevationCode() {
		return resevationCode;
	}

	public void setResevationCode(String resevationCode) {
		this.resevationCode = resevationCode;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public void setNumSeats(int numSeats) {
		this.numSeats = numSeats;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getHoldTime() {
		return holdTime;
	}

	public void setHoldTime(Date holdTime) {
		this.holdTime = holdTime;
	}

	
}
