package com.alura.hotel.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.alura.hotel.model.User;

public class UserDao {
	private EntityManagerFactory emf;

    public UserDao() {
    	emf = Persistence.createEntityManagerFactory("my-pu"); 
    }

    public boolean findUserByUsernameAndPassword(String username, String password) {
    	EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                User.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);
            if(query.getSingleResult() != null) {
            	return true;
            }
            else {
            	return false;
            }
            //return query.getSingleResult();
        } catch (NoResultException e) {
            // Handle if no user found
            return false;
        }
        finally {
        	em.close();
        }
    }
}
