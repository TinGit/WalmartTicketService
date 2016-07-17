package com.ticketService.DAO;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ticketService.domain.Customer;
import com.ticketService.domain.Seat;
import com.ticketService.domain.SeatHold;

@Repository
@Transactional
public class SeatHoldDAO implements ISeatHoldDAO{

	@PersistenceContext
	EntityManager em;
	
	public void holdSeat(SeatHold seathold) {
		em.persist(seathold);
	}

	public SeatHold getHoldSeat(int id) {
		return em.find(SeatHold.class, id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteSeatHold(int id){
		
		  Query q= em.createQuery("SELECT m FROM SeatHold m where m.seatHoldId = :id");
		  q.setParameter("id",id);
		  List<SeatHold> seatsHold = q.getResultList();

		  System.out.println("size===="+seatsHold.size());
		  if(!seatsHold.isEmpty()){
			  for(SeatHold sh:seatsHold){
				  System.out.println("size sh===="+sh.getSeatHoldId());

				  em.remove(sh);
			  }
		  }
		  
	}

	public List<SeatHold> getAllSeatHolds(){
		return em.createQuery("FROM SeatHold",SeatHold.class).getResultList();
	}
	
    /**
	 * 
	 * @return the number of seats that were reserved now released 
	 */
	public int deleteAllSeatHold(){
		  String selectQuery = "SELECT m FROM SeatHold m";  
		  List<SeatHold> seatHolds= em.createQuery(selectQuery).getResultList(); 
		  int count =0;
		  for (SeatHold m: seatHolds) {  
			  count = m.getSeats().size()+count;
		      em.remove(m);
		  }
		//int rows = em.createQuery("DELETE s FROM SeatHold sh.seats s").executeUpdate();
		return count;
	}
}
