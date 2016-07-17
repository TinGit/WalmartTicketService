package com.ticketService.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Venue {
	
	@Id @GeneratedValue
	private int venueId;
	
	@Enumerated(EnumType.STRING)
	private LevelName levelName;
	
	@Column(precision=16,scale=2)
	private float price;
	
	@Column(name="rows")
	private int rows;
	
	@Column(name="seatsInRow")
	private int seatsPerRow;
	
	public Venue(){}
	
	public Venue(LevelName levelName, float price, int rows, int seatsPerRow) {
		this.levelName = levelName;
		this.price = price;
		this.rows = rows;
		this.seatsPerRow = seatsPerRow;
	}

	public int getVenueId() {
		return venueId;
	}

	public void setVenueId(int venueId) {
		this.venueId = venueId;
	}

	public LevelName getLevelName() {
		return levelName;
	}

	public void setLevelName(LevelName levelName) {
		this.levelName = levelName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public void setSeatsPerRow(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}
	
}
