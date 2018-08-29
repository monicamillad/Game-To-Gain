package com.main.repositories;

import org.springframework.data.repository.CrudRepository;

import com.main.model.Student;

public interface IStudentRepository extends CrudRepository<Student, String>  {
	
	
}