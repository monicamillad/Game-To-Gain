package com.main.services;

import java.util.ArrayList;

import com.main.model.Teacher;

public interface ITeacherServices {
	
	public Teacher findByUsername(String username);
	public boolean teacherIsExist(String name);
	public boolean saveTeacher(Teacher teacher);
	public ArrayList<Teacher> getAllTeachers();
	public void updateTeacher(Teacher teacher);

}
