package com.ticketService.DAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ticketService.domain.Seat;
import com.ticketService.domain.SeatHold;
import com.ticketService.domain.Venue;

@Repository
@Transactional
public class SeatDAO implements ISeatDAO {

	@PersistenceContext
	EntityManager em;

	@Autowired
	IVenueDAO venueDao;

	@Autowired
	ISeatHoldDAO seatHoldDao;

	public SeatDAO() {

	}

	/**
	 * 
	 * @param venueId
	 *            -- the venue Id to use for the search(ranging from 1-4 or
	 *            empty)
	 * @return the number of seats available on the given venueId, if not given
	 *         it will return the total amount of seats available
	 */
	public int NumSeatsAvailable(Optional<Integer> venueId) {
		int venue = venueId.isPresent() ? venueId.get() : -1;

		// get all hold seats
		List<SeatHold> seatHolds = seatHoldDao.getAllSeatHolds();

		if (seatHolds.size() != 0) {
			// get all seats sorted
			List<Seat> seats = getAllSeatsSorted();
			int count = 0;
			boolean holded = false;
			for (Seat s : seats) {
				for (SeatHold sh : seatHolds) {
					if (sh.getSeats().contains(s)) {
						holded = true;
						break;
					}
				}
				if (holded == false) {
					if (venue != -1) {
						if (s.getVenue().getVenueId() == venue)
							count++;
					} else
						count++;
				} else {
					holded = false;
				}
			}

			return count;
		} else if (seatHolds.size() == 0 && venue == -1) {
			return getAllSeats().size();
		} else {
			return getSeatsByVenue(venueDao.getVenue(venue)).size();
		}

	}

	/***
	 * 
	 * @param minLevel
	 *            --- the minumum level id of venue
	 * @param maxLevel
	 *            --- the maximum level if of venue
	 * @param numSeats
	 *            --- number of seats requested by customer
	 * @return a list of seat objects and null is returned if numofSeats
	 *         required are not available
	 */
	public List<Seat> findSeats(Optional<Integer> minLevel, Optional<Integer> maxLevel, int numSeats) {

		int min = minLevel.isPresent() ? minLevel.get() : 1;
		int max = maxLevel.isPresent() ? maxLevel.get() : 4;
		
		List<Seat> seatsFound = new ArrayList<Seat>();
		
		if (min <= max) {
			
			seatsFound = searchByMinMaxLevel(min, max, numSeats);

		//	if (seatsFound.size() == numSeats)
			//	return seatsFound;// .subList(0, numSeats);
		//	else
				return seatsFound;
		} else
			return seatsFound;
	}

	/**
	 * Find seats with in the minimum and maximum level range of venue
	 * 
	 * @param min
	 *            the minumum level id of venue
	 * @param max
	 *            --- the maximum level if of venue
	 * @param numSeats
	 *            --- number of seats requested by customer
	 * @return the list of seats
	 */
	public List<Seat> searchByMinMaxLevel(int min, int max, int numSeats) {
		List<Seat> seatsFound = new ArrayList<Seat>();
		int seatCount = 0;

		// get all seatHolds
		List<SeatHold> seatHolds = seatHoldDao.getAllSeatHolds();

		// get all seats sorted within the venue level range
		List<Seat> seats = getSeatsByMinMaxSorted(min, max);

		if (seatHolds.size() != 0) {
			boolean holded = false;
			for (Seat s : seats) {
				for (SeatHold sh : seatHolds) {
					if (sh.getSeats().contains(s)) {
						holded = true;
						break;
					}
				}
				if (holded == false && seatCount < numSeats) {
					seatsFound.add(s);
					seatCount++;
				} else {
					holded = false;
				}
				if (seatCount == numSeats) {
					break;
				}
			}
		} else {
			for (int i = 0; i < numSeats; i++) {
				seatsFound.add(seats.get(i));
			}
		}

		return seatsFound;
	}

	public void createSeat(Seat seat) {
		em.persist(seat);
	}

	public List<Seat> getAllSeats() {
		return em.createQuery("FROM Seat", Seat.class).getResultList();
	}

	public List<Seat> getAllSeatsSorted() {
		return em.createQuery("select s from Seat s order by s.seatNumber ASC", Seat.class).getResultList();
	}

	public List<Seat> getSeatsByVenue(Venue venue) {
		Query q = em.createQuery("select distinct s from Seat s where s.venue = :venueObject", Seat.class);
		q.setParameter("venueObject", venue);
		return q.getResultList();
	}

	public List<Seat> getSeatsByMinMaxSorted(int min, int max) {
		Query q = em.createQuery(
				"select distinct s from Seat s where s.venue.venueId>= :min AND s.venue.venueId <= :max order by s.seatNumber ASC",
				Seat.class);
		q.setParameter("min", min);
		q.setParameter("max", max);
		return q.getResultList();

	}
}
