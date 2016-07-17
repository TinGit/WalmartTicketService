package com.ticketService.driverClass;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ticketService.DAO.ICustomerDAO;
import com.ticketService.DAO.ISeatDAO;
import com.ticketService.DAO.ISeatHoldDAO;
import com.ticketService.DAO.IVenueDAO;
import com.ticketService.domain.Customer;
import com.ticketService.domain.LevelName;
import com.ticketService.domain.Seat;
import com.ticketService.domain.SeatHold;
import com.ticketService.domain.Venue;
import com.ticketService.service.TicketService;

public class WalmartMainApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");

		// populateVenuAndSeatData(context);

		TicketService ticketServiceObj = context.getBean("ticketServiceImpl", TicketService.class);

		System.out.println("Below are number of seats available in each venue");
		System.out.println("==================================================");
		System.out.println();

		System.out.println("Number of seats in Orchestra ===> " + ticketServiceObj.numSeatsAvailable(Optional.of(1)));
		System.out.println("Number of seats in Main ===> " + ticketServiceObj.numSeatsAvailable(Optional.of(2)));
		System.out.println("Number of seats in Balcony 1 ===> " + ticketServiceObj.numSeatsAvailable(Optional.of(3)));
		System.out.println("Number of seats in Balcony 2 ===> " + ticketServiceObj.numSeatsAvailable(Optional.of(4)));

		System.out.println("Total number of seats available = " + ticketServiceObj.numSeatsAvailable(Optional.<Integer> empty()));

		boolean proceed = true;
		do {

			System.out.println("Type \"FH\" to find & hold a seat, \"R\" to reserve a seat, \"RL\" to release seats");
			Scanner sc1 = new Scanner(System.in);
			String operation = sc1.nextLine();
			if (operation.trim().equalsIgnoreCase("fh")) { // find & hold seat

				try {
					System.out.println("Provide the following details:");
					System.out.print("Enter number of seats you want to reserve:");
					Scanner s2 = new Scanner(System.in);
					int numSeats = s2.nextInt();

					System.out.println();

					System.out.println("Enter minimum level of venue for your seats");
					System.out.print(
							"Enter 1 for Orchestra, 2 for Main, 3 for Balcony1, 4 for Balcony2 and -1 for No preference:");
					s2 = new Scanner(System.in);
					int min = s2.nextInt();
					Optional<Integer> minLevel;
					if (min == -1)
						minLevel = Optional.<Integer> empty();
					else
						minLevel = Optional.of(min);

					System.out.println();

					System.out.println("Enter maximum level of venue for your seats");
					System.out.print(
							"Enter 1 for Orchestra, 2 for Main, 3 for Balcony1, 4 for Balcony2 and -1 for No preference:");
					s2 = new Scanner(System.in);
					int max = s2.nextInt();
					Optional<Integer> maxLevel;
					if (max == -1)
						maxLevel = Optional.<Integer> empty();
					else
						maxLevel = Optional.of(max);

					SeatHold seathold = ticketServiceObj.findAndHoldSeats(numSeats, minLevel, maxLevel,
							"tinbit.hirutu@gmail.com");
					if (seathold != null) {
						System.out.println(
								"Seats are hold successfully and your seathold Id is=" + seathold.getSeatHoldId());
					}
					else{
						System.out.println("Seats you want to hold are not available");
					}
				} catch (InputMismatchException e) {
					System.out.println("Please enter correct type");
				}
				System.out.println();
				System.out.println("=============================================");
			} else if (operation.trim().equalsIgnoreCase("r")) { // reserve seat
				System.out.println("Provide the following details to reserve your seats:");
				System.out.print("Enter the seathold Id:");
				Scanner s3 = new Scanner(System.in);
				try {
					int seatHoldId = s3.nextInt();
					String reservationCode = ticketServiceObj.reserveSeats(seatHoldId, "tinbit.hirutu@gmail.com");

					System.out.println("Your reservation confirmation code is:" + reservationCode);
				} catch (InputMismatchException e) {
					System.out.println("Please enter correct type");
				}

				System.out.println();
				System.out.println("=============================================");
			} else if (operation.trim().equalsIgnoreCase("rl")) {// release all
																	// seats
				int num = ticketServiceObj.releaseSeats();
				System.out.println("Number of total seats released are:" + num);
			} else {
				System.out.println("You have entered a wrong operation name");
			}
			System.out.println("Do you want to continue?(Y/N)");
			Scanner sc = new Scanner(System.in);
			String choice = sc.nextLine();
			if (choice.trim().substring(0, 1).equalsIgnoreCase("n"))
				proceed = false;

		} while (proceed);

          System.out.println("Exiting....");
	}

	/*
	 * used to populate the db with some data
	 */
	public static void populateVenuAndSeatData(ApplicationContext context) {
		IVenueDAO venuDao = context.getBean(IVenueDAO.class);
		ISeatDAO seatDao = context.getBean(ISeatDAO.class);
		ICustomerDAO custDao = context.getBean(ICustomerDAO.class);

		/*
		 * Populating Customer data
		 */
		custDao.createCustomer(new Customer("Tinbit", "Hirutu", "tinbit.hirutu@gmail.com"));
		custDao.createCustomer(new Customer("Jhon", "Smith", "jhon.smith@gmail.com"));
		custDao.createCustomer(new Customer("Mary", "Don", "mary.don@gmail.com"));

		/*
		 * Populating Venue data
		 */
		venuDao.createVenue(new Venue(LevelName.ORCHESTRA, 100, 25, 50));
		venuDao.createVenue(new Venue(LevelName.MAIN, 75, 20, 100));
		venuDao.createVenue(new Venue(LevelName.BALCONY1, 50, 15, 100));
		venuDao.createVenue(new Venue(LevelName.BALCONY2, 40, 15, 100));

		/* Populating Seat data */
		for (int i = 1; i <= 4; i++) {// each venue
			Venue venue = venuDao.getVenue(i);
			for (int row = 1; row <= venue.getRows(); row++) {
				for (int col = 1; col <= venue.getSeatsPerRow(); col++) {
					String seatNo = venue.getVenueId() + "" + row + "" + col;
					seatDao.createSeat(new Seat(Integer.parseInt(seatNo), venue));
				}
			}
		}
	}
}
