package com.main.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Course;
import com.main.repositories.ICourseRepository;

@Service
public class CourseServices implements ICourseServices {
	
	@Autowired
	ICourseRepository repository;
	
	@Override
	public boolean saveCourse(Course course) {
		if(repository.save(course) != null)
			return true;
		return false;
	}

	@Override
	public ArrayList<Course> getAll() {
		
		Iterable<Course> coursesIterator = repository.findAll();
		ArrayList<Course> allCourses = new ArrayList<Course>();
		for(Course course : coursesIterator){

			allCourses.add(course);
		}
		return allCourses;
	}

	@Override
	public Course findById(Long id) {
		
		Iterable<Course> coursesIterator = repository.findAll();
		for(Course course : coursesIterator){

			if(course.getCourseid()==id){
				return course ;
			}
		}
		return null;
	}

	@Override
	public Course findByName(String name) {
		
		Iterable<Course> coursesIterator = repository.findAll();
		for(Course course : coursesIterator){

			if(course.getName().equals(name)){
				return course ;
			}
		}
		return null;
	}
}
