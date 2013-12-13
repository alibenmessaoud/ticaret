package com.ticaret.ejbs;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ticaret.daos.DAOEvent;
import com.ticaret.daos.DAOUser;
import com.ticaret.entities.Event;
import com.ticaret.entities.InvitedUser;
import com.ticaret.entities.User;
import com.ticaret.helpers.Result;
import com.ticaret.helpers.Validate;
 
@Stateless
public class EJBUser {
	
	@EJB
	private DAOUser userDAO;
	@EJB
	private DAOEvent eventDAO;
	private Result result;
	private String errors;
	private Validate validate;
	
	public EJBUser() {
    	userDAO = new DAOUser();
		eventDAO = new DAOEvent();
		result = new Result();
		errors = "";
		validate = new Validate();
    }
	
	public User C(User u) {
		return userDAO.insert(u);
	}
		
	public User R(int id) {
		User u = new User();		
		u = userDAO.get(id);
		u.setUserPassword("********");
		u.setEvents(null);
		u.setInvitedUsers1(null);
		u.setInvitedUsers2(null);
		return u;
	}
	
	public User U(User u) {				
		u = userDAO.update(u);		
		return u;
	}
	
	public void D(int id) {				
		userDAO.remove(id);
	}

	public List<User> L() {
		List<User> L =  userDAO.list();
		for (int i = 0; i < L.size(); i++) {
			L.get(i).setEvents(null);
			L.get(i).setInvitedUsers1(null);
			L.get(i).setInvitedUsers2(null);			
		}
		return L;
	}
	
	public Result login(User u)	
	{	
		if (userDAO.daoVerifyLogin(u.getUserEmail(), u.getUserPassword()).size() == 1)
		{
			u = userDAO.daoVerifyLogin(u.getUserEmail(), u.getUserPassword()).get(0);
			result.setOperation("login");
			result.setMessage("ok");
			result.setId(u.getUserId());
		}
		else
		{
			result.setOperation("login");
			result.setMessage("failed");
		}
		return result;
	}
	
	public Result register(User u)
	{
		if (!validate.isText(u.getUserFname(), true))
			errors = errors + "First name is not valid.<br>";
		if (!validate.isText(u.getUserFname(), true))
			errors = errors + "Last name is not valid.<br>";
		if (!validate.isEmailAddress(u.getUserEmail(), true))
			errors = errors + "Email is not valid<br>";
		if (userDAO.daoEmailExist(u.getUserEmail()).size() >= 1)
			errors = errors + "Email is used in our system.<br>";
		if (u.getUserPassword().length() < 8 || u.getUserPassword().length() > 20)
			errors = errors + "Password must be between 8 & 20 characters.<br>";
		
		if (errors.length() == 0) {
			u.setUserDate(new Timestamp(new Date().getTime()));
			u = userDAO.insert(u);
			result.setOperation("register");
			result.setMessage("ok");
			result.setFlagId("user-id");
			result.setId(u.getUserId());			
		}
		else
		{
			result.setOperation("register");
			result.setMessage("failed");
			result.setFlagText("form-error");
			result.setText(errors);
		}
		
		return result;
	}
	
	public List<Event> userEvents(int id) {		
		User u = userDAO.get(id);
		List<Event> eventList = eventDAO.daoGetUserEvents(u);
		for (int i = 0; i < eventList.size(); i++) {
			eventList.get(i).setUser(null);
			eventList.get(i).setInvitedUsers(null);
		}
		return eventList;
	}
	
	public List<Event> userInvitations(int id) {		
		User u = userDAO.get(id);
		List<InvitedUser> invitationsList = eventDAO.daoGetUserInvitations(u);
		List<Event> eventList = null;
		for (int i = 0; i < invitationsList.size(); i++) {
			invitationsList.get(i).setUser1(null);
			invitationsList.get(i).setUser2(null);
			invitationsList.get(i).getEvent().setUser(null);
			invitationsList.get(i).getEvent().setInvitedUsers(null);			
		}
		return eventList;
	}
	
	
}
