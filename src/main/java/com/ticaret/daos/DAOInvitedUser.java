package com.ticaret.daos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import com.ticaret.entities.Event;
import com.ticaret.entities.InvitedUser;
import com.ticaret.entities.User;

 
@Stateless
public class DAOInvitedUser extends DAO<InvitedUser> {
	
    public DAOInvitedUser() {
        super(InvitedUser.class);
    }

	public List<InvitedUser> userEventAttendState(Event e, User u) {
		Query query = em.createQuery("select i from InvitedUser i where i.user1 = :user and i.event = :event");
		query.setParameter("user", u);
		query.setParameter("event", e);
		query.setMaxResults(1);
		return (List<InvitedUser>) query.getResultList();
	}

	public List<InvitedUser> userEventVerifyState(Event e, User u) {
		Query query = em.createQuery("select i from InvitedUser i where i.user1 = :user and i.event = :event");
		query.setParameter("user", u);
		query.setParameter("event", e);
		query.setMaxResults(1);
		return (List<InvitedUser>) query.getResultList();
		
	}
	
	public List<InvitedUser> userEventVerifyState(int ide, int idu) {
		User u = new DAOUser().get(idu);
		Event e = new DAOEvent().get(ide);
		Query query = em.createQuery("select i from InvitedUser i where i.user1 = :user and i.event = :event");
		query.setParameter("user", u);
		query.setParameter("event", e);
		query.setMaxResults(1);
		return (List<InvitedUser>) query.getResultList();
		
	}  
}