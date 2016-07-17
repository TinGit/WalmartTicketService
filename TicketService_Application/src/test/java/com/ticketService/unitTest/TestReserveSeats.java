package com.ticketService.unitTest;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ticketService.domain.SeatHold;
import com.ticketService.service.TicketService;

public class TestReserveSeats {

	ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");

	/*
	@Test
	public void testReserveOrchestraSeats() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		SeatHold seatToHold = ticketServiceObj.findAndHoldSeats(2, Optional.<Integer>empty(), Optional.of(1), "tinbit.hirutu@gmail.com");
		String reserveCode = ticketServiceObj.reserveSeats(seatToHold.getSeatHoldId(), "tinbit.hirutu@gmail.com");
		assertNotNull(reserveCode);
	}
	
	@Test
	public void testReserveMainSeats() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		SeatHold seatToHold = ticketServiceObj.findAndHoldSeats(1, Optional.of(2), Optional.of(2), "tinbit.hirutu@gmail.com");
		String reserveCode = ticketServiceObj.reserveSeats(seatToHold.getSeatHoldId(), "tinbit.hirutu@gmail.com");
		assertNotNull(reserveCode);
	}
	*/
}
