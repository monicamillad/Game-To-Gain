package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.model.Student;
import com.main.model.Teacher;
import com.main.model.User;
import com.main.services.ICourseServices;
import com.main.services.IGameServices;
import com.main.services.IStudentServices;
import com.main.services.ITeacherServices;


@Controller
public class LoginController {

	@Autowired
	ICourseServices courseServices;

	@Autowired
	IGameServices gameServices;

	@Autowired
	IStudentServices studentServices;

	@Autowired
	ITeacherServices teacherServices;

	@GetMapping("/login")
	public ModelAndView Login(){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("log_in");
		User user = new User() ;
		mv.addObject("user", user);
		return mv;
	}

	@PostMapping("/verifylogin")
	public ModelAndView VerifyLogin(@ModelAttribute User u){
		ModelAndView mv = new ModelAndView() ;

		if( studentServices.studentIsExist(u.getUsername()) ){

			Student s = new Student() ;

			s = studentServices.findByUsername(u.getUsername()) ;

			if(s.getPassword().equals(u.getPassword()))
			{

				return HomePage(s.getUsername()) ;
			}
			else
			{
				// invalid password
			}

		}
		else if( teacherServices.teacherIsExist(u.getUsername()) ){

			Teacher teacher = new Teacher() ;

			teacher = teacherServices.findByUsername(u.getUsername()) ;


			if(teacher.getPassword().equals(u.getPassword()))
			{
				return HomePage(teacher.getUsername()) ;

			}
			else
			{
				// invalid password
			}
		}
		// invalid username 
		mv.addObject("user", u );
		mv.setViewName("log_in");
		return mv ;
	}

	public ModelAndView HomePage(String username){ 

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
