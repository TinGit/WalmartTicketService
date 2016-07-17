package com.ticketService.scheduler;

import java.util.TimerTask;

import com.ticketService.domain.SeatHold;

public abstract class ScheduleState extends TimerTask  {

	public abstract void handle(SeatHold seatHold);
}
