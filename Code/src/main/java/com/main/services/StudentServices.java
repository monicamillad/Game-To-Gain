package com.main.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Student;
import com.main.repositories.IStudentRepository;

@Service
public class StudentServices implements IStudentServices{
	
	@Autowired
	IStudentRepository repository ;

	@Override
	public Student findByUsername(String username) {

		Iterable<Student> studentList = repository.findAll();
		
		for(Student student : studentList){
			if(student.getUsername().equals(username))
			{
				return student;
			}
		}
	return null;
	}

	@Override
	public boolean studentIsExist(String name) {

		Iterable<Student> studentList = repository.findAll() ;
		for(Student student : studentList){
			
			if(student.getUsername().equals(name)){
				return true ;
			}
		}
		
		return false;
	}

	@Override
	public boolean saveStudent(Student student) {
		
		if( repository.save(student) != null ){
			return true ;
		}
		
		return false;
	}

	@Override
	public ArrayList<Student> getAllStudent() {
		
		Iterable<Student> studentIterator = repository.findAll();
		ArrayList<Student> allStudents = new ArrayList<Student>() ;
		
		for(Student student : studentIterator){
			allStudents.add(student) ;
		}
		
		return allStudents;
	}

	@Override
	public void updateStudent(Student student) {

		repository.delete(student);
		repository.save(student);
		
	}

}
