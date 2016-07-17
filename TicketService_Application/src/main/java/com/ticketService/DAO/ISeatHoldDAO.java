package com.ticketService.DAO;

import java.util.Date;
import java.util.List;

import com.ticketService.domain.Customer;
import com.ticketService.domain.Seat;
import com.ticketService.domain.SeatHold;

public interface ISeatHoldDAO {

	public void holdSeat(SeatHold seathold);
	public SeatHold getHoldSeat(int id);
	public void deleteSeatHold(int id);
	public List<SeatHold> getAllSeatHolds();
	public int deleteAllSeatHold();
}
