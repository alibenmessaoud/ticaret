package com.ticaret.daos;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import com.ticaret.entities.Event;
import com.ticaret.entities.InvitedUser;
import com.ticaret.entities.User;

@Stateless
public class DAOEvent extends DAO<Event> {

	public DAOEvent() {
		super(Event.class);
	}
	
	public List<Event> daoGetUserEvents(User u) {
		Query query = em.createQuery("SELECT e FROM Event e where e.user = :user"); 		
		query.setParameter("user", u);
		return (List<Event>) query.getResultList();
	}

	public List<InvitedUser> daoGetUserInvitations(User u) {
		Query query = em.createQuery("select iu from InvitedUser iu where iu.user1 = :invitedUser");
		query.setParameter("invitedUser", u);
		List<InvitedUser> l = query.getResultList();		
		return  l;
	}
}