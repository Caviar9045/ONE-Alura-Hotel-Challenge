package com.alura.hotel.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.alura.hotel.model.Reservation;

public class ReservationDao {
	private EntityManagerFactory emf;

	public ReservationDao() {
		emf = Persistence.createEntityManagerFactory("my-pu");
	}

	public long saveReservation(Date fechaEntrada, Date fechaSalida, double valor, String formaPago) {

		EntityManager em = emf.createEntityManager();

		Reservation reservation = new Reservation();
		reservation.setFechaEntrada(fechaEntrada);
		reservation.setFechaSalida(fechaSalida);
		reservation.setValor(valor);
		reservation.setFormaPago(formaPago);

		try {
			em.getTransaction().begin();
			em.persist(reservation);
			em.getTransaction().commit();
			return reservation.getId();
		} finally {
			em.close();
		}
	}

	public void delete(Long givenId) {
		EntityManager em = emf.createEntityManager();
		try {
			Reservation reservation = em.find(Reservation.class, givenId);

			if (reservation != null) {
				em.getTransaction().begin();
				em.remove(reservation);
				em.getTransaction().commit();
			} else {
			}
		} catch (Exception exception) {

		}
		finally {
			em.close();
		}
	}
	
	public void update(Long givenId, String fechaCheckIn, String fechaCheckOut, double valor, String forma_pago) throws ParseException {
		EntityManager em = emf.createEntityManager();
		Reservation reservation = em.find(Reservation.class, givenId);
		
		String pattern = "yyyy-MM-dd"; // The pattern should match the input string format
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        
		if (reservation != null) {
			Date fechaEntrada = dateFormat.parse(fechaCheckIn);
			Date fechaSalida = dateFormat.parse(fechaCheckOut);
			
			reservation.setFechaEntrada(fechaEntrada);
			reservation.setFechaSalida(fechaSalida);
			reservation.setValor(valor);
			reservation.setFormaPago(forma_pago);

            em.getTransaction().begin();
            em.merge(reservation); // Use merge for detached entities
            em.getTransaction().commit();
        } else {
        }

        em.close();
	}

	public void close() {
		emf.close();
	}
}
