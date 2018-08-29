package com.main.model;
import java.util.ArrayList;
import java.util.List;

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
public class Course {
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long Courseid ;
	
	private String name ;
	private String category ;
	private String description ;
	private int lowerAge ;
	
	@ManyToOne
	@JoinColumn(name="username") 
	private Teacher teacher ;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="course")
	private List<Game> games ;
	
	@ManyToMany(mappedBy="courses1")
	private List<Student> students ;
	
	public Course(){
		games=new ArrayList<Game>();
		teacher=new Teacher();
		students = new ArrayList<Student>() ;
		//cancled = new ArrayList<Game>() ;
	}
	
	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> g) {
		
		games.clear(); 
		for(int i=0;i<games.size();i++)
		{
			games.add(g.get(i));
		}
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher.setName(teacher.getName());
		this.teacher.setEmail(teacher.getEmail());
		this.teacher.setPassword(teacher.getPassword());
		this.teacher.setUsername(teacher.getUsername());
		this.teacher.setCpassword(teacher.getCpassword());
		this.teacher.setDateOfBirth(teacher.getDateOfBirth());
		this.teacher.setPhoneNumber(teacher.getPhoneNumber());
		this.teacher.setGender(teacher.getGender());
		this.teacher.setType(teacher.getType());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLowerAge() {
		return lowerAge;
	}

	public void setLowerAge(int lowerAge) {
		this.lowerAge = lowerAge;
	}

	public Long getCourseid() {
		return Courseid;
	}

	public void setCourseid(Long courseid) {
		Courseid = courseid;
	}
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
}
