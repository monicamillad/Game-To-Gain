package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.model.Course;
import com.main.model.Game;
import com.main.model.Teacher;
import com.main.services.IGameServices;
import com.main.services.ITeacherServices;

@Controller
public class TeacherController {
	
	@Autowired
	ITeacherServices teacherServices;
	
	@Autowired
	IGameServices gameService;
	
	@PostMapping("/create_course/{username}")
	public ModelAndView CreateCourse(@PathVariable String username){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("create_course");	
		mv.addObject("un",username);
		Course course = new Course();		
		mv.addObject("course",course);
		return mv ;
	}
	
	
	@PostMapping("/create_game/{username}/{Courseid}")
	public ModelAndView CreateGame(@PathVariable String username,@PathVariable Long Courseid){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("create_game");
        Game game = new Game();
        mv.addObject("game",game);
		mv.addObject("un",username);
		mv.addObject("cid",Courseid);
		return mv ;
	}
	
	@PostMapping("/add_collaborator_form/{username}/{Gameid}")
	public ModelAndView AddCollaboratorForm(@PathVariable String username,@PathVariable Long Gameid){
		
		ModelAndView mv = new ModelAndView();
		System.out.println("game id : "+Gameid);
        mv.addObject("gameid",Gameid);
		mv.addObject("un",username);
		
		Teacher t = new Teacher() ;
		
		//String name="" ;
		
		mv.addObject("t",t);
		
		mv.setViewName("add_collaborator_form");
		
		return mv ;
	}
	
	@PostMapping("/add_collaborator/{username}/{Gameid}")
	public ModelAndView AddCollaborator(@ModelAttribute Teacher t,@PathVariable String username,@PathVariable Long Gameid){
		System.out.println("d5lttttttttt");
		ModelAndView mv = new ModelAndView();
		System.out.println(t.getName());
		Teacher teacher = teacherServices.findByUsername(t.getName()) ;
		
		Game game = gameService.findById(Gameid) ;
		
		teacher.getGames().add(game) ;
		game.getCollaborators().add(teacher) ;
        
		teacherServices.saveTeacher(teacher) ;
		gameService.saveGame(game) ;
		
		mv.addObject("un",username);
		
		mv.setViewName("teacher_home");
		
		return mv ;
	}
	
}
