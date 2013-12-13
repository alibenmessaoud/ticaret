package com.ticaret.ejbs;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ticaret.daos.DAOEvent;
import com.ticaret.daos.DAOInvitedUser;
import com.ticaret.daos.DAOUser;
import com.ticaret.entities.Event;
import com.ticaret.entities.InvitedUser;
import com.ticaret.entities.User;
import com.ticaret.helpers.Result;
import com.ticaret.helpers.Validate;

@Stateless
public class EJBInvitedUser {
	@EJB
	private DAOUser userDAO;
	@EJB
	private DAOEvent eventDAO;
	@EJB
	private DAOInvitedUser iuDAO ;
	private Result result;
	private String errors;
	private Validate validate;
	
	public EJBInvitedUser() {
		userDAO = new DAOUser();
		eventDAO = new DAOEvent();
		iuDAO = new DAOInvitedUser();
		result = new Result();
		errors = "";
		validate = new Validate();
	}
	
	public InvitedUser C(InvitedUser iu) {
		return iuDAO.insert(iu);
	}

	public InvitedUser R(int id) {
		return iuDAO.get(id);
	}

	public List<InvitedUser> L() {
		return iuDAO.list();
	}
	
	public InvitedUser userEventAttendState(int ide, int idu) {
		Event e = eventDAO.get(ide);
		User u = userDAO.get(idu);
		InvitedUser iuDefault = new InvitedUser();
		iuDefault.setIuState("None");
		if (iuDAO.userEventVerifyState(e, u).size() == 1) {
			List<InvitedUser> ius = iuDAO.userEventAttendState(e, u);
			InvitedUser iu = ius.get(0);
			iu.getUser1().setEvents(null);
			iu.getUser1().setInvitedUsers1(null);
			iu.getUser1().setInvitedUsers2(null);
			iu.getUser2().setEvents(null);
			iu.getUser2().setInvitedUsers1(null);
			iu.getUser2().setInvitedUsers2(null);
			iu.getEvent().setUser(null);
			iu.getEvent().setInvitedUsers(null);
			return iu;
		} else
			return iuDefault;
	}

	public Result userEventChangeState(int ide, int idu, String state) {
		Event e = eventDAO.get(ide);
		User u = userDAO.get(idu);
		InvitedUser iu = null;
		if (iuDAO.userEventVerifyState(e, u).size() == 1) {
			iu = iuDAO.userEventAttendState(e, u).get(0);
			iu.setIuState(state);
			iuDAO.update(iu);
			result.setOperation("change-state");
			result.setMessage("ok");
		}
		else
		{
			invite(ide, idu, idu);
			userEventChangeState(ide, idu, state);
		}
		return result;
	}

	public Result invite(int ide, int idu1, int idu2) {		
		Event e = eventDAO.get(ide);
		User u1 = userDAO.get(idu1);
		User u2 = userDAO.get(idu2);

		InvitedUser iu = new InvitedUser();
		if (iuDAO.userEventVerifyState(e, u1).size() == 0) {
			iu.setEvent(e);
			iu.setUser1(u1);
			iu.setUser2(u2);
			iu.setIuState("None");
			iu.setIuDate(new Timestamp(new Date().getTime())); 
			iuDAO.insert(iu); 
			result.setOperation("invite");
			result.setMessage("ok");
		}
		else
		{
			result.setOperation("invite");
			result.setMessage("invited");
		}
		 
		return result;
	}
}
