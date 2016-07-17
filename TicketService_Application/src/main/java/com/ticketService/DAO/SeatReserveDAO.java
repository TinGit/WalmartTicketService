package com.ticketService.DAO;


import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ticketService.domain.SeatReserve;

@Repository
@Transactional
public class SeatReserveDAO implements ISeatReserveDAO {

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	ICustomerDAO custDao;
	
	public void reserveSeat(SeatReserve seatReserve) {
		em.persist(seatReserve);
	}

	  /**
     * 
     * @param custEmail -- customer email
     * @param seatHoldId	-- seat hold id 
     * @return a string which is a confirmation code
     */
	public String generateReservationConfirmationCode(String custEmail, int seatHoldId) {
		int custId = custDao.findByEmail(custEmail).getCustId();
		String key = custId+seatHoldId+""+custEmail.substring(0, 2);
		
		byte[] byteKey = key.getBytes();

		int sum=0;
		for(byte b:byteKey){
			sum = sum+b;
		}
		 
		String reservationCode = UUID.randomUUID().toString().split("-")[0]+"-" +seatHoldId+""+sum;

		return reservationCode;
	}

	public boolean isReserved(int seatHoldId){
		Query q = em.createQuery("select s from SeatReserve s where s.seatReserveId =:id");
		q.setParameter("id", seatHoldId);
		List<SeatReserve> seatsReserved = q.getResultList();
		
		if(seatsReserved.size()==0)
			return false;
		else
			return true;
	}
}
