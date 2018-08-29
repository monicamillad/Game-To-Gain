package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.main.model.Student;
import com.main.model.Teacher;
import com.main.services.IStudentServices;
import com.main.services.ITeacherServices;

@Controller
public class NotificationController {
	
	@Autowired
	IStudentServices studentServices;
	
	@Autowired
	ITeacherServices teacherServices ;
	
	@GetMapping("/show_student_notification/{username}")
	public ModelAndView StudentNotification(@ModelAttribute Student s ,@PathVariable String username){
		
		ModelAndView mv = new ModelAndView();
		
		Student student = studentServices.findByUsername(username) ;
		System.out.println(student.getUsername());
		System.out.println(student.getNotifGame().size());
		mv.addObject("student",student) ;
		mv.addObject("un",username) ;
		mv.setViewName("student_notifications");
		
		return mv ;
	}

	@GetMapping("/show_teacher_notification/{username}")
	public ModelAndView TeacherNotification(@ModelAttribute Teacher t ,@PathVariable String username){
		
		ModelAndView mv = new ModelAndView();
		
		Teacher teacher = teacherServices.findByUsername(username) ;
		
		mv.addObject("teacher",teacher) ;
		mv.addObject("un",username) ;
		mv.setViewName("teacher_notifications");
		
		return mv ;
	}
	
}
