package com.ticaret.daos;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ticaret.daos.DAO;
import com.ticaret.entities.User;
 
@Stateless
public class DAOUser extends DAO<User> {
 
    public DAOUser() {
        super(User.class);
    }
    
    public List<User> daoVerifyLogin(String userEmail, String userPassword) {
		//l.info("daoVerifyLogin()"); 
		Query query = em.createQuery("select u from User u where u.userEmail = :userEmail and u.userPassword = :userPassword"); 
		query.setParameter("userEmail", userEmail);
		query.setParameter("userPassword", userPassword);
		return (List<User>) query.getResultList();
	}
	
	public List<User> daoEmailExist(String email) {
		//l.info("daoEmailExist"); 
		Query query = em.createQuery("select u from User u where u.userEmail = :email"); 
		query.setParameter("email", email);
		return (List<User>) query.getResultList();
	}
}