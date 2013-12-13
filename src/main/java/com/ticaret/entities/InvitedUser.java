package com.ticaret.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the invited_users database table.
 * 
 */
@Entity
@Table(name="invited_users")
@NamedQuery(name="InvitedUser.findAll", query="SELECT i FROM InvitedUser i")
public class InvitedUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="iu_id", unique=true, nullable=false)
	private Integer iuId;

	@Column(name="iu_date")
	private Timestamp iuDate;

	@Column(name="iu_state", length=10)
	private String iuState;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iu_event_id", nullable=false)
	private Event event;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iu_to_id", nullable=false)
	private User user1;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="iu_from_id", nullable=false)
	private User user2;

	public InvitedUser() {
	}

	public Integer getIuId() {
		return this.iuId;
	}

	public void setIuId(Integer iuId) {
		this.iuId = iuId;
	}

	public Timestamp getIuDate() {
		return this.iuDate;
	}

	public void setIuDate(Timestamp iuDate) {
		this.iuDate = iuDate;
	}

	public String getIuState() {
		return this.iuState;
	}

	public void setIuState(String iuState) {
		this.iuState = iuState;
	}

	public Event getEvent() {
		return this.event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser1() {
		return this.user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return this.user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

}