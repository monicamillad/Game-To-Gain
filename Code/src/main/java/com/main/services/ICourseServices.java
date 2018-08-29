package com.main.services;

import java.util.ArrayList;

import com.main.model.Course;

public interface ICourseServices {
	
	public boolean saveCourse(Course course);
	public ArrayList<Course> getAll();
	public Course findById(Long id);
	public Course findByName( String name ) ;
}
