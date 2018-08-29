package com.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class GameNotification {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long notifId;
	
	@ManyToOne
	@JoinColumn(name="susername")
	private Student s ;
	
	private String notif ;

	public GameNotification(){
		
		s = new Student() ;
	}

	public long getNotifId() {
		return notifId;
	}

	public void setNotifId(long notifId) {
		this.notifId = notifId;
	}

	public Student getS() {
		return s;
	}

	public void setS(Student s) {
		this.s = s ;
	}

	public String getNotif() {
		return notif;
	}

	public void setNotif(String notif) {
		this.notif = notif;
	}
	
}
