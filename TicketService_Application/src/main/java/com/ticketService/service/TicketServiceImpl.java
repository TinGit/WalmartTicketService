package com.ticketService.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticketService.DAO.ICustomerDAO;
import com.ticketService.DAO.ISeatDAO;
import com.ticketService.DAO.ISeatHoldDAO;
import com.ticketService.DAO.ISeatReserveDAO;
import com.ticketService.domain.Customer;
import com.ticketService.domain.Seat;
import com.ticketService.domain.SeatHold;
import com.ticketService.domain.SeatReserve;
import com.ticketService.scheduler.ScheduleHoldSeatRelease;
import com.ticketService.scheduler.ScheduleState;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	ISeatDAO seatDao;

	@Autowired
	ICustomerDAO customerDao;

	@Autowired
	ISeatHoldDAO seatHoldDao;

	@Autowired
	ISeatReserveDAO seatReserveDao;

	//@Autowired
	ScheduleHoldSeatRelease schedulehold;
	
	private final int HOLD_EXPIRY_DURATION = 30; //this means 30minutes
	
	public int numSeatsAvailable(Optional<Integer> venueLevel) {
		int result = seatDao.NumSeatsAvailable(venueLevel);
		return result;
	}

	public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel,
			String customerEmail) {
		// search or find
		synchronized (this) {
			List<Seat> seatsFound = seatDao.findSeats(minLevel, maxLevel, numSeats);

			// if search successful Hold seats
			if (seatsFound.size() != 0) {
				// find customer by email
				Customer cust = customerDao.findByEmail(customerEmail);
				SeatHold seatHold = new SeatHold(cust, new Date(), seatsFound);
				seatHoldDao.holdSeat(seatHold);

				
				//set schedule to reset seat hold after 3minutes
				Timer timer = new Timer();
				Calendar cal = Calendar.getInstance();
				cal.setTime(seatHold.getHoldTime());
				cal.add(Calendar.MINUTE, HOLD_EXPIRY_DURATION);
				ScheduleState stateObj =new ScheduleHoldSeatRelease();
				stateObj.handle(seatHold);
				timer.schedule(stateObj, cal.getTime());
				
				return seatHold;
			} else
				return null;
		}
	}

	/* reserve holdseat & return reservation confirmation code */
	public String reserveSeats(int seatHoldId, String customerEmail) {
		
		// find SeatHold object
		SeatHold seatHold = seatHoldDao.getHoldSeat(seatHoldId);

		// find customer by email
		Customer cust = customerDao.findByEmail(customerEmail);
		
		boolean reserved = seatReserveDao.isReserved(seatHoldId);
		
		if(reserved==false){
			if(seatHold.getCustomer().equals(cust)) //Only the person holding the seat should make/confirm the reservation
			{
				String reservationCode = seatReserveDao.generateReservationConfirmationCode(customerEmail, seatHoldId);
				SeatReserve seatReserve = new SeatReserve(seatHoldId, reservationCode, new Date(),seatHold.getHoldTime(),seatHold.getCustomer(),seatHold.getSeats().size());
				seatReserveDao.reserveSeat(seatReserve);

				return reservationCode;
			}
		}
			
		return null; 
	}
	
	 /**
		 * This method will make the system ready for the next event by cleaning the SeatHold table
		 * @return the number of seats released that were on hold 
		 */
		public int releaseSeats(){
			return seatHoldDao.deleteAllSeatHold();
		}


}
