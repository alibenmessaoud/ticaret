package com.ticaret.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the events database table.
 * 
 */
@Entity
@Table(name="events")
@NamedQuery(name="Event.findAll", query="SELECT e FROM Event e")
public class Event implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="event_id", unique=true, nullable=false)
	private Integer eventId;

	@Column(name="event_date")
	private Timestamp eventDate;

	@Column(name="event_description", length=2147483647)
	private String eventDescription;

	@Column(name="event_end_date", length=20)
	private String eventEndDate;

	@Column(name="event_image", length=50)
	private String eventImage;

	@Column(name="event_location", length=100)
	private String eventLocation;

	@Column(name="event_name", length=100)
	private String eventName;

	@Column(name="event_start_date", length=20)
	private String eventStartDate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="event_user_id", nullable=false)
	private User user;

	@OneToMany(mappedBy="event", fetch=FetchType.LAZY)
	private List<InvitedUser> invitedUsers;

	public Event() {
	}

	public Integer getEventId() {
		return this.eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Timestamp getEventDate() {
		return this.eventDate;
	}

	public void setEventDate(Timestamp eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventDescription() {
		return this.eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventEndDate() {
		return this.eventEndDate;
	}

	public void setEventEndDate(String eventEndDate) {
		this.eventEndDate = eventEndDate;
	}

	public String getEventImage() {
		return this.eventImage;
	}

	public void setEventImage(String eventImage) {
		this.eventImage = eventImage;
	}

	public String getEventLocation() {
		return this.eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventName() {
		return this.eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventStartDate() {
		return this.eventStartDate;
	}

	public void setEventStartDate(String eventStartDate) {
		this.eventStartDate = eventStartDate;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<InvitedUser> getInvitedUsers() {
		return this.invitedUsers;
	}

	public void setInvitedUsers(List<InvitedUser> invitedUsers) {
		this.invitedUsers = invitedUsers;
	}

	public InvitedUser addInvitedUser(InvitedUser invitedUser) {
		getInvitedUsers().add(invitedUser);
		invitedUser.setEvent(this);

		return invitedUser;
	}

	public InvitedUser removeInvitedUser(InvitedUser invitedUser) {
		getInvitedUsers().remove(invitedUser);
		invitedUser.setEvent(null);

		return invitedUser;
	}

}