package com.ticaret.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_id", unique=true, nullable=false)
	private Integer userId;

	@Column(name="user_date")
	private Timestamp userDate;

	@Column(name="user_email", length=100)
	private String userEmail;

	@Column(name="user_fname", length=50)
	private String userFname;

	@Column(name="user_lname", length=50)
	private String userLname;

	@Column(name="user_location", length=50)
	private String userLocation;

	@Column(name="user_password", length=20)
	private String userPassword;

	@OneToMany(mappedBy="user", fetch=FetchType.LAZY)
	private List<Event> events;

	@OneToMany(mappedBy="user1", fetch=FetchType.LAZY)
	private List<InvitedUser> invitedUsers1;

	@OneToMany(mappedBy="user2", fetch=FetchType.LAZY)
	private List<InvitedUser> invitedUsers2;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Timestamp getUserDate() {
		return this.userDate;
	}

	public void setUserDate(Timestamp userDate) {
		this.userDate = userDate;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserFname() {
		return this.userFname;
	}

	public void setUserFname(String userFname) {
		this.userFname = userFname;
	}

	public String getUserLname() {
		return this.userLname;
	}

	public void setUserLname(String userLname) {
		this.userLname = userLname;
	}

	public String getUserLocation() {
		return this.userLocation;
	}

	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Event addEvent(Event event) {
		getEvents().add(event);
		event.setUser(this);

		return event;
	}

	public Event removeEvent(Event event) {
		getEvents().remove(event);
		event.setUser(null);

		return event;
	}

	public List<InvitedUser> getInvitedUsers1() {
		return this.invitedUsers1;
	}

	public void setInvitedUsers1(List<InvitedUser> invitedUsers1) {
		this.invitedUsers1 = invitedUsers1;
	}

	public InvitedUser addInvitedUsers1(InvitedUser invitedUsers1) {
		getInvitedUsers1().add(invitedUsers1);
		invitedUsers1.setUser1(this);

		return invitedUsers1;
	}

	public InvitedUser removeInvitedUsers1(InvitedUser invitedUsers1) {
		getInvitedUsers1().remove(invitedUsers1);
		invitedUsers1.setUser1(null);

		return invitedUsers1;
	}

	public List<InvitedUser> getInvitedUsers2() {
		return this.invitedUsers2;
	}

	public void setInvitedUsers2(List<InvitedUser> invitedUsers2) {
		this.invitedUsers2 = invitedUsers2;
	}

	public InvitedUser addInvitedUsers2(InvitedUser invitedUsers2) {
		getInvitedUsers2().add(invitedUsers2);
		invitedUsers2.setUser2(this);

		return invitedUsers2;
	}

	public InvitedUser removeInvitedUsers2(InvitedUser invitedUsers2) {
		getInvitedUsers2().remove(invitedUsers2);
		invitedUsers2.setUser2(null);

		return invitedUsers2;
	}

}