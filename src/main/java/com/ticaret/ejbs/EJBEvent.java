package com.ticaret.ejbs;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ticaret.daos.DAOEvent;
import com.ticaret.daos.DAOUser;
import com.ticaret.entities.Event;
import com.ticaret.helpers.Result;
import com.ticaret.helpers.Validate;

@Stateless
public class EJBEvent {
	@EJB
	private DAOUser userDAO;
	@EJB
	private DAOEvent eventDAO;
	private Result result;
	private String errors;
	private Validate validate;
	
	public EJBEvent() {
		userDAO = new DAOUser();
		eventDAO = new DAOEvent();
		result = new Result();
		errors = "";
		validate = new Validate();
	}
	
	public Event C(Event u) {
		return eventDAO.insert(u);
	}
		
	public Event R(int id) {
		Event e = new Event();		
		e = eventDAO.get(id);
		e.setUser(null);
		e.setInvitedUsers(null);		
		return e;
	}
	
	public Event U(Event u) {				
		u = eventDAO.update(u);		
		return u;
	}
	
	public void D(int id) {				
		eventDAO.remove(id);
	}
	
	public List<Event> L()
	{
		List<Event> L = eventDAO.list();
		for (int i = 0; i < L.size(); i++) {
			L.get(i).getUser().setEvents(null);
			L.get(i).getUser().setInvitedUsers1(null);
			L.get(i).getUser().setInvitedUsers2(null);
			L.get(i).setInvitedUsers(null);
		}
		return L;
	}

	public Result eventAdd(Event e) {				
		if (e.getEventName().equals(""))
			errors = errors + "Event name is empty.<br>";
		if (!validate.isText(e.getEventName(), true))
			errors = errors + "Event name is invalid.<br>";
		if (e.getEventLocation().equals(""))
			errors = errors + "Location is empty.<br>";
		if (!validate.isText(e.getEventLocation(), true))
			errors = errors + "Location is invalid.<br>";		
		if (e.getEventStartDate().equals(""))
			errors = errors + "Start date is empty.<br>";
		if (e.getEventEndDate().equals(""))
			errors = errors + "End date is empty.";
		if (!validate.isText(e.getEventDescription(), true))
			errors = errors + "Description is invalid.<br>";
		if (e.getUser().getUserId().equals(""))
			errors = errors + "User is empty";
		if (e.getEventImage().equals(""))
			e.setEventImage("none.png");
		
		if (errors.length() == 0) {
			e.setEventDate(new Timestamp(new Date().getTime()));
			eventDAO.insert(e);			
			result.setOperation("add-event");
			result.setMessage("ok");
			result.setFlagId("event-id");
			result.setId(e.getEventId());
		}
		else
		{
			result.setOperation("add-event");
			result.setMessage("failed");
			result.setFlagText("form-error");
			result.setText(errors);
		}
		

		return result;
	}
	
	
	
}
