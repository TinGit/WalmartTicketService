package com.ticketService.DAO;

import com.ticketService.domain.Venue;

public interface IVenueDAO {

	public void createVenue(Venue venue);
	public Venue getVenue(int id);
}
