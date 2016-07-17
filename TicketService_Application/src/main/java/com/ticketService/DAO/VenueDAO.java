package com.ticketService.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ticketService.domain.Venue;

@Repository
@Transactional
public class VenueDAO implements IVenueDAO{

	@PersistenceContext
	EntityManager em;
	
	public void createVenue(Venue venue) {
		em.persist(venue);
	}

	public Venue getVenue(int id) {
		return em.find(Venue.class, id);
	}

}
