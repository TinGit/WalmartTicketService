package com.ticketService.DAO;

import com.ticketService.domain.SeatReserve;

public interface ISeatReserveDAO {

	public void reserveSeat(SeatReserve seatReserve);
	public String generateReservationConfirmationCode(String custEmail,int seatHoldId);
	public boolean isReserved(int seatHoldId);
}
