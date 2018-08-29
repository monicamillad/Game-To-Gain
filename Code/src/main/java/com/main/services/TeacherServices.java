package com.main.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Teacher;
import com.main.repositories.ITeacherRepository;

@Service
public class TeacherServices implements ITeacherServices{
	
	@Autowired
	ITeacherRepository repository;
	
	@Override
	public Teacher findByUsername(String username) {
		
		Iterable<Teacher> teacherList = repository.findAll();
		
			for(Teacher teacher : teacherList){
				if(teacher.getUsername().equals(username)){
					
					return teacher;
				}
			}
		return null;
	}
	
	@Override
	public boolean teacherIsExist(String name) {
		
		Iterable<Teacher> teacherList = repository.findAll() ;
		for(Teacher teacher : teacherList){
			
			if(teacher.getUsername().equals(name)){
				return true ;
			}
		}
		
		return false;
	}
	

	@Override
	public boolean saveTeacher(Teacher teacher) {
		
		if( repository.save(teacher) != null ){
			return true ;
		}
		
		return false;
	}
	
	@Override
	public ArrayList<Teacher> getAllTeachers() {
		
		Iterable<Teacher> teacherIterator = repository.findAll();
		ArrayList<Teacher> allTeachers = new ArrayList<Teacher>() ;
		
		for(Teacher teacher : teacherIterator){
			allTeachers.add(teacher) ;
		}
		
		return allTeachers;
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		
		repository.delete(teacher);
		repository.save(teacher);
		
	}

}
