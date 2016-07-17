package com.ticketService.scheduler;

import java.util.List;
import java.util.TimerTask;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticketService.DAO.ISeatHoldDAO;
import com.ticketService.DAO.SeatHoldDAO;
import com.ticketService.domain.SeatHold;

/*
 * This class main purpose is to remove seat hold if the time to hold is expired, 
 * 
 */

public class ScheduleHoldSeatRelease extends ScheduleState{
	
	private SeatHold seathold;
		
	ISeatHoldDAO seatHoldDao = new SeatHoldDAO();
	
	public ScheduleHoldSeatRelease(){
		//System.out.println("In scheduling ======");
	}
	
	public void handle(SeatHold seatHold) {
		this.seathold =seatHold;
		//System.out.println("seatholdid in handle===="+this.seathold.getSeatHoldId());
	}

	@Override
	public void run() {
		//System.out.println("seatholdid in run===="+this.seathold.getSeatHoldId());

		int id = this.seathold.getSeatHoldId();
		seatHoldDao.deleteSeatHold(id);
	}

	
	public SeatHold getSeathold() {
		return seathold;
	}

	public void setSeathold(SeatHold seathold) {
		this.seathold = seathold;
	}



}
