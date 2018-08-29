package com.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CommentNotification {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long notificationId;
	
	@ManyToOne
	@JoinColumn(name="tusername")
	private Teacher t ;
	
	private String commentNotif ;
	
	public CommentNotification(){
		
		t = new Teacher() ;
	}

	public long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}

	public Teacher getT() {
		return t;
	}

	public void setT(Teacher t) {
		this.t = t;
	}

	public String getCommentNotif() {
		return commentNotif;
	}

	public void setCommentNotif(String commentNotif) {
		this.commentNotif = commentNotif;
	}

}
