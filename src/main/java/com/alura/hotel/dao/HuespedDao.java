package com.alura.hotel.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.alura.hotel.model.Huesped;
import com.alura.hotel.model.Reservation;

public class HuespedDao {
    private EntityManagerFactory emf;

    public HuespedDao() {
        emf = Persistence.createEntityManagerFactory("my-pu");
    }
    
    public void saveHuesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, String telefono, Long reserva_id) {
    	
        EntityManager em = emf.createEntityManager();
        
        Huesped	huesped = new Huesped();
        huesped.setNombre(nombre);
        huesped.setApellido(apellido);
        huesped.setFechaNacimiento(fecha_nacimiento);
        huesped.setNacionalidad(nacionalidad);
        huesped.setTelefono(telefono);
        huesped.setReserva_id(reserva_id);

        try {
            em.getTransaction().begin();
            em.persist(huesped);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(Long givenId) {
		EntityManager em = emf.createEntityManager();
		try {
			Huesped huesped = em.find(Huesped.class, givenId);

			if (huesped != null) {
				em.getTransaction().begin();
				em.remove(huesped);
				em.getTransaction().commit();
			} else {
			}
		} catch (Exception exception) {

		}
		finally {
			em.close();
		}
	}
    
    public void update(Long givenId, String nombre, String apellido, String fecha_nacimiento_String, String nacionalidad, String telefono, Long reserva_id) throws ParseException {
		EntityManager em = emf.createEntityManager();
		Huesped huesped = em.find(Huesped.class, givenId);
		
		String pattern = "yyyy-MM-dd"; // The pattern should match the input string format
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        
		if (huesped != null) {
			Date fecha_nacimiento = dateFormat.parse(fecha_nacimiento_String);
			
			huesped.setNombre(nombre);
			huesped.setApellido(apellido);
			huesped.setFechaNacimiento(fecha_nacimiento);
			huesped.setNacionalidad(nacionalidad);
			huesped.setTelefono(telefono);
			huesped.setReserva_id(reserva_id);

            em.getTransaction().begin();
            em.merge(huesped); // Use merge for detached entities
            em.getTransaction().commit();
        } else {
        }

        em.close();
	}
    
    public void close() {
        emf.close();
    }
}
