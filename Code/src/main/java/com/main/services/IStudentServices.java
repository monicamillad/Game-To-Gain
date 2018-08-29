package com.main.services;

import java.util.ArrayList;

import com.main.model.Student;

public interface IStudentServices {

	public Student findByUsername(String username);
	public boolean studentIsExist(String name);
	public boolean saveStudent( Student student);
	public ArrayList<Student> getAllStudent();
	public void updateStudent(Student student);
}
