package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.model.Course;
import com.main.model.Game;
import com.main.model.Student;
import com.main.model.Teacher;
import com.main.model.User;
import com.main.services.ICourseServices;
import com.main.services.IGameServices;
//import com.main.services.IStudentServices;
//import com.main.services.ITeacherServices;
import com.main.services.IStudentServices;
import com.main.services.ITeacherServices;


@Controller 
public class RegisterController {

		
	@Autowired
	ICourseServices courseServices;
	
	@Autowired
	IGameServices gameServices;
	
	@Autowired
	IStudentServices studentServices;
	
	@Autowired
	ITeacherServices teacherServices;
	
	@GetMapping("/sign_up")
	public ModelAndView SignUp(Model model ){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sign_up");
		User user = new User() ;
		model.addAttribute("u",user);
		mv.addObject("user", user);
		return mv;
	}

	public static boolean TeacherVerification(String email) {
		return email.contains(".edu");
	}

	
	@PostMapping("/verify")
	public ModelAndView Verify(@ModelAttribute User u){
		
		String msg="";

		ModelAndView mv = new ModelAndView();
		
		mv.addObject("un",u.getUsername()); 
		
		if( !studentServices.studentIsExist(u.getUsername()) && !teacherServices.teacherIsExist(u.getUsername()) ){
			
			if( u.getType().equals("teacher") ){
				
				if (TeacherVerification(u.getEmail())&&u.getPassword().equals(u.getCpassword())){
					
					Teacher t = new Teacher(u) ;
					
					teacherServices.saveTeacher(t) ;
					
					mv.addObject("teacher", t );
					
					mv.setViewName("teacher_home");
					
					
					ArrayList <Course> courses=new ArrayList<Course>();
					courses=courseServices.getAll();
					
					ArrayList <Game> games=new ArrayList<Game>();
					games=gameServices.getAll();
					
					mv.addObject("courses",courses) ;
					
					mv.addObject("games",games) ;
					
				}
				else if( !TeacherVerification(u.getEmail())){
					
					// to sign up as teacher you should have an academic email
					msg="pleas enter an academic email";
					u.setError(msg);
					mv.addObject("user", u );
					mv.setViewName("sign_up");
					return mv ;
				}
			}
			else{
				
				Student s = new Student(u) ;
				
				studentServices.saveStudent(s) ;
				
				mv.addObject("student", s );
				
				mv.setViewName("student_home");
				ArrayList <Course> courses=new ArrayList<Course>();
				courses=courseServices.getAll();
			
				
				ArrayList <Game> games=new ArrayList<Game>();
				games=gameServices.getAll();
				mv.addObject("courses",courses) ;
				
				mv.addObject("games",games) ;
				mv.setViewName("student_home");
				
			}
			
			if (!u.getPassword().equals(u.getCpassword())){
				
				msg="confirm password doesn't match the password";
				u.setError(msg);
				mv.addObject("user", u );
				mv.setViewName("sign_up");
				return mv ;
			}
			
		}
		else{
			
			msg="this username is taken";
			u.setError(msg);
			mv.addObject("user", u );
			mv.setViewName("sign_up");
		}
		
		return mv ;
	}

}
