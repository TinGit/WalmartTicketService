package com.ticketService.unitTest;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ticketService.domain.Seat;
import com.ticketService.domain.SeatHold;
import com.ticketService.service.TicketService;
import com.ticketService.service.TicketServiceImpl;


public class TicketServiceTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
	/*
	@Test
	public void testOrchestraSeatsAvailable() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		int numOfSeats = ticketServiceObj.numSeatsAvailable(Optional.of(1));
		assertNotNull("ORCHESTRA venue seats are null", numOfSeats);
		assertEquals(1250, numOfSeats);
	}
	
	@Test
	public void testMainSeatsAvailable() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		int numOfSeats = ticketServiceObj.numSeatsAvailable(Optional.of(2));
		assertNotNull(numOfSeats);
		assertEquals(2000, numOfSeats);
	}
	@Test
	public void testBalcony1SeatsAvailable() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		int numOfSeats = ticketServiceObj.numSeatsAvailable(Optional.of(3));
		assertNotNull(numOfSeats);
		assertEquals(1500, numOfSeats);
	}
	@Test
	public void testBalcony2SeatsAvailable() {
		TicketService ticketServiceObj = context.getBean("ticketServiceImpl",TicketService.class);
		int numOfSeats = ticketServiceObj.numSeatsAvailable(Optional.of(4));
		assertNotNull(numOfSeats);
		assertEquals(1500, numOfSeats);
	}
	
	*/
	
}
