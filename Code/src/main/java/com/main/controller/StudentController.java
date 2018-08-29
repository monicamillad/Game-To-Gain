package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.model.Course;
import com.main.model.Student;
import com.main.services.IAchievementServices;
import com.main.services.ICourseServices;
import com.main.services.IGameServices;
import com.main.services.StudentServices;

@Controller
public class StudentController {
	
	@Autowired
	IAchievementServices achievementServices ;
	
	@Autowired
	IGameServices gameService;
	
	@Autowired
	ICourseServices courseServices;
	
	@Autowired
	StudentServices studentServices ;
	
	@PostMapping("/enroll/{username}/{Courseid}")
	public ModelAndView RegisterCourse(@PathVariable String username,@PathVariable Long Courseid){
		
		ModelAndView mv = new ModelAndView();
		
		Course course = courseServices.findById(Courseid) ;
		
		Student student =studentServices.findByUsername(username) ;
		
		student.getCourses1().add(course) ;
		course.getStudents().add(student) ;
		
		courseServices.saveCourse(course) ;
		studentServices.saveStudent(student) ;
		
		student =studentServices.findByUsername(username) ;
		mv.addObject("student",student) ;
		mv.addObject("course",course) ;
		mv.addObject("un",username);
		mv.setViewName("course_view_student2"); 
		
		return mv ;
	}
	
	
}
