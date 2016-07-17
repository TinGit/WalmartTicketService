package com.ticketService.domain;

import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Seat{

	@Id @GeneratedValue
	private int seatId;
	
	@Column(name="seatNum")
	private int seatNumber;
	
	@OneToOne
	@JoinColumn(name="venuId")
	private Venue venue;
	
	public Seat() {
	}
	
	public Seat(int seatNumber, Venue venue) {
		this.seatNumber=seatNumber;
		this.venue=venue;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Venue getVenue() {
		return venue;
	}

	public void setVenue(Venue venue) {
		this.venue = venue;
	}		
}
