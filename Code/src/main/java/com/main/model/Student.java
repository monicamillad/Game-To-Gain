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
public class Student {

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
	private int totalScore;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="student")
	private List<Achievement> achievements ;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(name="studentscourses", joinColumns=@JoinColumn(name="studentid" , referencedColumnName="username"), inverseJoinColumns=@JoinColumn(name="courseid",referencedColumnName="Courseid"))
	private List<Course> courses1 ;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="s")
	private List<GameNotification> notifGame ;
	
	public Student(){
		totalScore = 0 ;
		achievements = new ArrayList<Achievement>() ;
		courses1 = new ArrayList<Course>() ;
		notifGame = new ArrayList<GameNotification>() ;
	}
	
	public Student(User u){
		
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

	public void UpdateStudentTotalScore(int score) {
		totalScore+=score;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	
	public List<Achievement> getAchievements() {
		return achievements;
	}

	public void setAchievements(List<Achievement> achievements) {
		this.achievements = achievements;
	}

	public List<Course> getCourses1() {
		return courses1;
	}

	public void setCourses1(List<Course> courses1) {
		this.courses1 = courses1;
	}

	public List<GameNotification> getNotifGame() {
		return notifGame;
	}

	public void setNotifGame(List<GameNotification> notifGame) {
		this.notifGame = notifGame;
	}
	
}