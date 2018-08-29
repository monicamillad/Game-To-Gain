package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.model.Student;
import com.main.model.Teacher;
import com.main.services.ICourseServices;
import com.main.services.IGameServices;
import com.main.services.IStudentServices;
import com.main.services.ITeacherServices;


@Controller 
public class controler {

	@Autowired 
	ITeacherServices teacherServices; 
	
	@Autowired 
	IStudentServices studentServices; 
	
	@Autowired 
	IGameServices gameServices; 
	
	@Autowired 
	ICourseServices courseServices; 
	
	@RequestMapping("/")
	public String start(){
		
		return "index" ;
	}
	
	@RequestMapping("/logout")
	public String logout(){
		
		return "index" ;
	}
	
	@GetMapping("/home_page/{username}")
	public ModelAndView getHomePage(@PathVariable String username){
			
		ModelAndView mv = new ModelAndView() ;
		
		mv.addObject("un",username); 
		if( studentServices.studentIsExist(username) ){
		
			Student s = studentServices.findByUsername(username);

			mv.addObject("student",s) ;	
			mv.setViewName("student_home");
			
		}
		else if( teacherServices.teacherIsExist(username) ){
			
			Teacher teacher= teacherServices.findByUsername(username);

			mv.addObject("teacher",teacher) ;
			mv.setViewName("teacher_home");
										
		}
		return mv;
	}
	
	

	
	
}
