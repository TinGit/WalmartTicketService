package com.ticketService.DAO;

import java.util.List;
import java.util.Optional;

import com.ticketService.domain.Seat;
import com.ticketService.domain.Venue;

public interface ISeatDAO {

	public int NumSeatsAvailable(Optional<Integer> venueId);
	public List<Seat> findSeats(Optional<Integer> minLevel,Optional<Integer> maxLevel, int numSeats );
	public List<Seat> searchByMinMaxLevel(int min,int max,int numSeats);
	public void createSeat(Seat seat);
	public List<Seat> getAllSeats();
	public List<Seat> getAllSeatsSorted();
	public List<Seat> getSeatsByVenue(Venue venue);
}
