package com.ticketService.unitTest;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ticketService.domain.SeatHold;
import com.ticketService.service.TicketService;

public class TestFindAndHoldSeats {

	ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
	
	/*
	@Test
	public void testFindAndHoldOrchestraSeats() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		SeatHold s = ticketServiceObj.findAndHoldSeats(10, Optional.of(1), Optional.of(1), "tinbit.hirutu@gmail.com");
		assertNotNull(s);
		int remainingSeatsAvailable = ticketServiceObj.numSeatsAvailable(Optional.of(1));
		assertEquals(1240, remainingSeatsAvailable);		
	}
	
	@Test
	public void testFindAndHoldMainSeats() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		SeatHold seatToHold = ticketServiceObj.findAndHoldSeats(10, Optional.of(2), Optional.of(2), "tinbit.hirutu@gmail.com");
		assertNotNull( seatToHold);
		
		int remainingSeatsAvailable = ticketServiceObj.numSeatsAvailable(Optional.of(2));
		assertEquals(1990, remainingSeatsAvailable);
			
	}
	@Test
	public void testFindAndHoldBalcony1Seats() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		SeatHold seatToHold = ticketServiceObj.findAndHoldSeats(500, Optional.of(3), Optional.of(3), "tinbit.hirutu@gmail.com");
		assertNotNull(seatToHold);
		int remainingSeatsAvailable = ticketServiceObj.numSeatsAvailable(Optional.of(3));
		assertEquals(1000, remainingSeatsAvailable);
			
	}
	@Test
	public void testFindAndHoldBalcony2Seats() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		SeatHold seatToHold = ticketServiceObj.findAndHoldSeats(500, Optional.of(2), Optional.of(2), "tinbit.hirutu@gmail.com");
		assertNotNull(seatToHold);
		int remainingSeatsAvailable = ticketServiceObj.numSeatsAvailable(Optional.ofNullable(1));
		assertEquals(1000, remainingSeatsAvailable);
			
	}
	*/
}
