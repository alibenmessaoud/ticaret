package com.ticaret.services;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.ticaret.daos.DAOEvent;
import com.ticaret.daos.DAOUser;
import com.ticaret.ejbs.EJBEvent;
import com.ticaret.ejbs.EJBInvitedUser;
import com.ticaret.ejbs.EJBUser;
import com.ticaret.entities.Event;
import com.ticaret.entities.InvitedUser;
import com.ticaret.entities.User;
import com.ticaret.helpers.Result;

@Path("/app")
@Produces(MediaType.APPLICATION_JSON)
public class TimeService {
	
	@EJB 
	EJBUser ejbu;	
	@EJB 
	EJBEvent ejbe;
	@EJB
	EJBInvitedUser ejbiu;
	
    
    @GET
    @Path("/users/ali")
    public User hello()    
    {
    	User u = new User();
    	u.setUserFname("Ali");    	
    	return u;
    }          
    
    @GET
	@Path("/users")
	public List<User> getUsers() {		
		return ejbu.L() ;
	}
    
    @POST
   	@Path("/users/register")
    @Consumes(MediaType.APPLICATION_JSON)
   	public Result userRegister(User u) { 
    	return ejbu.register(u);   
   	}
    
    @POST
   	@Path("/users/login")
    @Consumes(MediaType.APPLICATION_JSON)
   	public Result userLogin(User u) { 
    	return ejbu.login(u);
   	}
    
    @GET
   	@Path("/users/{id}")
   	public User getUser(@PathParam("id") int id) {
   		User u = ejbu.R(id);
   		return u ;
   	}
    
        
    @Path("users/{id}/events")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public List<Event> getUserEvents(@PathParam("id") int id)
	{	 
		return ejbu.userEvents(id);	 
	}
    
    @Path("users/{id}/invitations")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public int getUserInvitations(@PathParam("id") int id)
	{	 
		return ejbu.userInvitations(id).size();
    	//return new DAOEvent().daoGetUserInvitations(new DAOUser().get(id)).get(0);
	}
    
    @Path("events")
   	@Produces({ MediaType.APPLICATION_JSON })
   	@GET
   	public List<Event> getEvents()
   	{	 
   		return ejbe.L();
   	}
    
    @Path("events/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public Event getEvent(@PathParam("id") int id)
	{	 
		return ejbe.R(id);
	}    
    
    @Path("events/add")
   	@Produces({ MediaType.APPLICATION_JSON })
   	@POST
   	public Result addEvent(Event e)
   	{	 
   		return ejbe.eventAdd(e);
   	}
    
    @Path("users/{idu}/events/{ide}/state")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public InvitedUser userEventAttendState(@PathParam("ide") int ide, @PathParam("idu") int idu ) throws UnsupportedEncodingException 
	{				
		return ejbiu.userEventAttendState(ide, idu);			
	}
    
    @Path("users/{idu}/events/{ide}/state/{state}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public Result userEventChangeState(@PathParam("ide") int ide, @PathParam("idu") int idu, @PathParam("state") String state) throws UnsupportedEncodingException {
		String decodedState = URLDecoder.decode(state, "UTF-8");
		return ejbiu.userEventChangeState(ide, idu, decodedState);
	}
    
    @Path("users/{idu1}/invite/{idu2}/to/{ide}")
	@Produces({ MediaType.APPLICATION_JSON })
	@GET
	public Result invite(@PathParam("ide") int ide, @PathParam("idu1") int idu1, @PathParam("idu2") int idu2) throws UnsupportedEncodingException {
		return ejbiu.invite(ide, idu1, idu2);	
	}
    
    

    
    
    public TimeService() {
		ejbu = new EJBUser();
		ejbe = new EJBEvent();
		ejbiu = new EJBInvitedUser();
	}
}

