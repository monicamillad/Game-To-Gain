package com.main.model;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Game{
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private long id;
	
	private String name;
	private String type;
	private int numberOfQ ;
	private int rate ;
	private String description;
	private boolean cancelled ;
	private long cId ;
	private String lastComment ;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="game")
	private List<Question> questions;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="game1")
	private List<Comment> comments ;
	
	@ManyToMany(mappedBy="games")
	private List<Teacher> collaborators ;

	@ManyToOne
	@JoinColumn(name="courseid") 
	private Course course ;

	public Game(){
		
		rate=0 ;
		course =new Course();
		questions = new ArrayList<Question>() ;
		numberOfQ = 0 ;
		collaborators = new ArrayList<Teacher>() ;
		comments = new ArrayList<Comment>() ;
		cancelled = false ;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumberOfQ() {
		return numberOfQ;
	}

	public void setNumberOfQ(int numberOfQ) {
		this.numberOfQ = numberOfQ;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = new ArrayList<Question>() ;
		
		for( int i=0 ; i<questions.size() ; i++ ){
			
			this.questions.add(questions.get(i)) ;
		}
	}

	public List<Teacher> getCollaborators() {
		return collaborators;
	}

	public void setCollaborators(List<Teacher> collaborators) {
		this.collaborators = new ArrayList<Teacher>();
		for( int i=0 ; i<collaborators.size() ; i++ ){
			
			this.collaborators.add(collaborators.get(i)) ;
		}
	}
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}

	public String getLastComment() {
		return lastComment;
	}

	public void setLastComment(String lastComment) {
		this.lastComment = lastComment;
	}
	
}