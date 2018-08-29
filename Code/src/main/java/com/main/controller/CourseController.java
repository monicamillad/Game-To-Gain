package com.main.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.model.Course;
import com.main.model.Student;
import com.main.model.Teacher;
import com.main.services.ICourseServices;
import com.main.services.IStudentServices;
import com.main.services.ITeacherServices;

@Controller
public class CourseController {

	@Autowired
	ICourseServices services;
	
	@Autowired 
	ITeacherServices teacherServices; 
	
	@Autowired 
	IStudentServices studentServices; 
	
	@PostMapping("/save_course/{username}")
	public ModelAndView SaveCourse(@PathVariable String username,@ModelAttribute Course course){
		
		Teacher teacher = teacherServices.findByUsername(username) ;
		course.setTeacher(teacher);
		services.saveCourse(course);
		System.out.println("save_course "+username);
		return ViewCourse(course,username) ;
	}
	
//	@PostMapping("/display_course")
//	public ModelAndView DisplayCourse(@ModelAttribute Course course){ 
//		System.out.println(course.getCourseid());
//		return ViewCourse(course) ;
//	}
	
	@GetMapping("/display_course/{username}/{id}")
	public ModelAndView DisplayCourse(@PathVariable String username,@PathVariable Long id){ 
		
		Course course=new Course();
		course=services.findById(id);
		
		return ViewCourse(course,username) ;
	}
	
	@GetMapping("/all-courses/{username}")
	public ModelAndView AllCourses(@PathVariable String username){ 
		
		ArrayList<Course> courses =services.getAll();
		ModelAndView mv=new ModelAndView();
		mv.addObject("un",username);
		mv.addObject("courses",courses);
		mv.setViewName("all_courses");
		return mv ;
	}
	@GetMapping("/category/{username}/{categ}")
	public ModelAndView Categories(@PathVariable String username,@PathVariable String categ){ 
		
		ArrayList<Course> courses=services.getAll();
		
		for(int i=0;i<courses.size();i++){
			
			if(!courses.get(i).getCategory().equals(categ)){
				
				courses.remove(i);
				i--;
			}
		}
		
		ModelAndView mv=new ModelAndView();
		
		mv.addObject("un",username);
		mv.addObject("courses",courses);
		mv.addObject("category",categ);
		
		if(!username.equals("null")){
			
			mv.setViewName("categories1");
		}
		else{
			
			mv.setViewName("categories2");
		}
		
		return mv ;
	}
	
	public ModelAndView ViewCourse(Course course,String username){ 
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("course",course);
		mv.addObject("un",username);
		
		if( studentServices.studentIsExist(username) ){
			
			mv.setViewName("course_view_student");
			Student s = studentServices.findByUsername(username) ;
			
			for(int i=0;i<s.getCourses1().size();i++){
				
				if(s.getCourses1().get(i).getCourseid()==course.getCourseid()){
					
					mv.setViewName("course_view_student2");
					break;
				}
			}
		}
		else if( teacherServices.teacherIsExist(username) ){
			
			mv.setViewName("course_view_teacher2");
			Teacher teacher = teacherServices.findByUsername(username) ;
			for(int i=0;i<teacher.getCourses().size();i++){
				
				if(teacher.getCourses().get(i).getCourseid()==course.getCourseid()){
					
					mv.setViewName("course_view_teacher");
					break;
				}
			}
										
		}
		
		return mv;
	}
}
