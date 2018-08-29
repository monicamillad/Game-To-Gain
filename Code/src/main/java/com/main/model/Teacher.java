package com.main.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Teacher {
	
	@Id
	private String username;
	
	private String name;
	private String password;
	private String cpassword;
	private String email;
	private String gender;
	private String phoneNumber;
	private String dateOfBirth;
	private String type;
	
	@OneToMany(mappedBy="teacher",cascade=CascadeType.ALL)
	private List<Course> courses ;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="teachersgames", joinColumns=@JoinColumn(name="teacherid" , referencedColumnName="username"), inverseJoinColumns=@JoinColumn(name="gameid",referencedColumnName="id"))
	private List<Game> games ;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="teacherscancelledgames", joinColumns=@JoinColumn(name="teacherid" , referencedColumnName="username"), inverseJoinColumns=@JoinColumn(name="gameid",referencedColumnName="id"))
	private List<Game> cancelled ;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="t")
	private List<CommentNotification> notifComment ;
	
	public Teacher(){
		
		courses = new ArrayList<Course>() ; 
		games = new ArrayList<Game>() ;
		cancelled = new ArrayList<Game>() ;
		notifComment = new ArrayList<CommentNotification>() ;
	}
	
	public Teacher(User u){
		
		courses = new ArrayList<Course>() ; 
		games = new ArrayList<Game>() ;
		cancelled = new ArrayList<Game>() ;
		setName(u.getName());
		setEmail(u.getEmail());
		setPassword(u.getPassword());
		setUsername(u.getUsername());
		setCpassword(u.getCpassword());
		setDateOfBirth(u.getDateOfBirth());
		setPhoneNumber(u.getPhoneNumber());
		setGender(u.getGender());
		setType(u.getType());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Game> getCancelled() {
		return cancelled;
	}

	public void setCancelled(List<Game> cancelled) {
		this.cancelled = cancelled;
	}

	public List<CommentNotification> getNotifComment() {
		return notifComment;
	}

	public void setNotifComment(List<CommentNotification> notifComment) {
		this.notifComment = notifComment;
	}

}