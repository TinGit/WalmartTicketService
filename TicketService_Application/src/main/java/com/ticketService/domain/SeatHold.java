package com.ticketService.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.hibernate.annotations.Cascade;

@Entity
public class SeatHold {

	@Id @GeneratedValue
	private int seatHoldId;
	
	@OneToOne
	@JoinColumn(name="customerId")
	private Customer customer;
	
	@Temporal(TemporalType.DATE)
	private Date holdTime;
	
	@OneToMany
	@JoinTable(name="SeatHold_Seat",
		joinColumns=@JoinColumn(name="seatHoldId"),
		inverseJoinColumns = @JoinColumn(name="seatId")
	)
	private List<Seat> seats = new ArrayList<Seat>();
	
	public SeatHold(){
		
	}
	
	public SeatHold(Customer customer, Date holdTime, List<Seat> seats) {
		this.customer = customer;
		this.holdTime = holdTime;
		this.seats = seats;
	}

	public int getSeatHoldId() {
		return seatHoldId;
	}

	public void setSeatHoldId(int seatHoldId) {
		this.seatHoldId = seatHoldId;
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

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	
	
}
