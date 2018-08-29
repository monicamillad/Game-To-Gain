package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.main.model.Achievement;
import com.main.model.Comment;
import com.main.model.Course;
import com.main.model.Game;
import com.main.model.Question;
import com.main.model.Student;
import com.main.model.Teacher;
import com.main.services.IAchievementServices;
import com.main.services.ICommentServices;
import com.main.services.ICourseNotification;
import com.main.services.ICourseServices;
import com.main.services.IGameNotification;
import com.main.services.IGameServices;
import com.main.services.IQuestionServices;
import com.main.services.IStudentServices;
import com.main.services.ITeacherServices;

@Controller
public class GameController {
	
@Autowired
IGameServices gameService;

@Autowired
IQuestionServices questionService ;

@Autowired
ITeacherServices teacherServices;

@Autowired
IStudentServices studentServices;

@Autowired
ICourseServices courseServices;

@Autowired
IAchievementServices achievementServices ;

@Autowired
ICommentServices commentServices ;

@Autowired
ICourseNotification gameNotification ;

@Autowired
IGameNotification commentNotification ;

@PostMapping("/create_question/{username}/{Courseid}")
public ModelAndView CreateQuestion(@ModelAttribute Game game,@PathVariable String username,@PathVariable Long Courseid){
	
	Teacher t = teacherServices.findByUsername(username) ;
	
	Course course = courseServices.findById(Courseid) ;
	
	game.setCourse(course);
	game.getCollaborators().add(t) ;
	t.getGames().add(game) ;
	
	gameService.saveGame(game) ;
	
    ModelAndView mv=new ModelAndView();
    Question question =new Question();
    mv.addObject("question",question);
    mv.addObject("game",game);
    mv.addObject("un",username);
    mv.addObject("cid",Courseid);
    
    if(game.getType().equals("MultipleChoice")){
    	
		mv.setViewName("add_game_mcq");
    }
	else if(game.getType().equals("TrueFalse")){
		
		mv.setViewName("add_game_tf");
    }
	
	return mv;
}

@PostMapping("/add_new_question/{username}/{gameid}")
public ModelAndView AddNewQuestion(@PathVariable String username,@PathVariable Long gameid){
	
	ModelAndView mv=new ModelAndView();
	
	Game game = gameService.findById(gameid) ;
    Question question =new Question();
    
    mv.addObject("question",question);
    mv.addObject("game",game) ;
    mv.addObject("cid",game.getCourse().getCourseid()) ;
    mv.addObject("un",username) ;
    
    if(game.getType().equals("MultipleChoice")){
    	
		mv.setViewName("add_game_mcq");
    }
	else if(game.getType().equals("TrueFalse")){
		
		mv.setViewName("add_game_tf");
    }
    
    return mv ;
}

@PostMapping("/add_question/{username}/{Courseid}/{gameid}")
public ModelAndView AddQuestion(@ModelAttribute Question q ,@PathVariable String username,@PathVariable Long Courseid,@PathVariable Long gameid){
	
	Game game= gameService.findById(gameid) ;
	
	q.setGame(game);
	
	questionService.saveQuestion(q) ; 

    ModelAndView mv=new ModelAndView();
    Question question =new Question();
    mv.addObject("question",question);
    mv.addObject("game",game);
    mv.addObject("un",username);
    mv.addObject("cid",Courseid);
    
	if(game.getType().equals("MultipleChoice")){
		
		mv.setViewName("add_game_mcq");
    }
	else if(game.getType().equals("TrueFalse")){
		
		mv.setViewName("add_game_tf");
    }
	
	return mv;
}
	
@PostMapping("/save_game/{username}/{Courseid}/{gameid}")
public ModelAndView SaveGame(@ModelAttribute Question q,@PathVariable String username,@PathVariable Long Courseid,@PathVariable Long gameid ){
	
	Game game = gameService.findById(gameid) ;
	
	q.setGame(game);
	questionService.saveQuestion(q) ;
	
	Course course = courseServices.findById(Courseid) ;
	
	ModelAndView mv=new ModelAndView();
	mv.addObject("course",course) ;
	mv.addObject("un",username);
	mv.setViewName("course_view_teacher");
	
	gameNotification.notifyGame(course, game.getName());
	
	return mv ;
}

@PostMapping("/start_game/{username}/{gameid}")
public ModelAndView StartGame(@ModelAttribute Question qq,@PathVariable String username,@PathVariable Long gameid){
	
	ModelAndView mv = new ModelAndView();
	
	Game game= gameService.findById(gameid) ;
	
	if( studentServices.studentIsExist(username) ){
		
		Achievement achievement = new Achievement() ;
		
		if( achievementServices.isExist( username,gameid) ){
			
			achievement = achievementServices.find( username, gameid ) ;
			achievementServices.resetLastScore(achievement);
		}
		else{
			
			Student student=studentServices.findByUsername(username) ;
			
			achievement.setStudent(student);
			achievement.setGameId(game.getId());
			achievement.setGameName(game.getName());
			
			achievementServices.saveAchievement(achievement) ;
		}
		
		Question q = game.getQuestions().get(0) ;
		
		mv.addObject("question",q);
		mv.addObject("un",username);
		mv.addObject("game",game);
		mv.addObject("count",0);
		if( game.getType().equals("MultipleChoice") ){
			
			mv.setViewName("play_game_mcq");
		}
		else{
			
			mv.setViewName("play_game_tf");
		}
	}
	else {
		
		Question q = game.getQuestions().get(0) ;
		
		mv.addObject("question",q);
		mv.addObject("un",username);
		mv.addObject("game",game);
		mv.addObject("count",0);
		if( game.getType().equals("MultipleChoice") ){
			
			mv.setViewName("play_game_mcq");
		}
		else{
			
			mv.setViewName("play_game_tf");
		}
	}

	
	return mv ;
} 

@PostMapping("/check_answer/{username}/{gameid}/{count}")
public ModelAndView CheckAnswer(@ModelAttribute Question qq,@PathVariable String username,@PathVariable Long gameid,@PathVariable int count){
	
	ModelAndView mv = new ModelAndView();
	
	Game game = gameService.findById(gameid) ;
	
	Question q=game.getQuestions().get(count) ;
	
	if( studentServices.studentIsExist(username) ){
		
		if( q.getAnswer().equals(qq.getCuurentAnswer()) ){
			
			achievementServices.updateScore(username, gameid, 1);
		}
	}
	
	count++;
	if( count==game.getQuestions().size() ){
		
		if( studentServices.studentIsExist(username) ){
			
			achievementServices.saveAchievement(achievementServices.updateMax(username, gameid)) ;
			Student student=studentServices.findByUsername(username);
			mv.addObject("student",student);
			mv.setViewName("student_home") ;
		}
		else{
			
			Teacher teacher= teacherServices.findByUsername(username);
			mv.addObject("teacher",teacher) ;
			
			mv.setViewName("teacher_home") ;
		}
		
		mv.addObject("un",username);
		
	}
	else{
		
		q = game.getQuestions().get(count) ;
		
		mv.addObject("question",q);
		
		mv.addObject("un",username);
		mv.addObject("game",game);
		mv.addObject("count",count);
		
		if( game.getType().equals("MultipleChoice") ){
			
			mv.setViewName("play_game_mcq");
		}
		else{
			
			mv.setViewName("play_game_tf");
		}
	}
	
	return mv ;
}

public ModelAndView ViewGame(Game game,String username){ 
	
	ModelAndView mv = new ModelAndView();
	
	if( studentServices.studentIsExist(username) ){
		
		mv.setViewName("game_view");
	}
	else if( teacherServices.teacherIsExist(username) ){
		
		mv.setViewName("game_view");
		Teacher teacher = teacherServices.findByUsername(username) ;
		for( int i=0 ; i<teacher.getGames().size() ; i++ ){
			
			if( teacher.getGames().get(i).getId()==game.getId() ){
				
				mv.setViewName("game_view_teacher");
				break ;
			}
		}
	}
	
	mv.addObject("game",game);
	mv.addObject("un",username);
	mv.addObject("course",game.getCourse());
	
	return mv;
}

@GetMapping("/display_game/{username}/{id}")//Edited
public ModelAndView DisplayGame(@PathVariable String username,@PathVariable Long id){ 
	
	Game game = gameService.findById(id);
	return ViewGame(game,username) ;
}

@GetMapping("/copy_game_form/{username}/{Gameid}")
public ModelAndView CopyGame(@ModelAttribute Game game,@PathVariable String username,@PathVariable Long Gameid){
	
    ModelAndView mv=new ModelAndView();
    
    Course course = new Course() ;
    
    mv.addObject("un",username);
    mv.addObject("gid",Gameid);
    mv.addObject("c",course) ;
    
    mv.setViewName("copy_game_form");
    
	return mv;
}

@GetMapping("/copy_game/{username}/{Gameid}")
public ModelAndView CopyGameForm(@ModelAttribute Course c , @PathVariable String username,@PathVariable Long Gameid){
	 
    ModelAndView mv=new ModelAndView();
    
    Course course = courseServices.findByName(c.getName()) ;
   
    Game game1 = gameService.findById(Gameid) ;
    
    Game game2 = new Game() ;
    
    Teacher teacher = teacherServices.findByUsername(username);
    
    game2.setCourse(course);
    game2.setName(game1.getName());
    game2.setDescription(game1.getDescription());
    game2.setType(game1.getType());
    game2.setNumberOfQ(game1.getNumberOfQ());
    game2.setCollaborators(game1.getCollaborators());
    game2.getCollaborators().add(teacher) ;
    teacher.getGames().add(game2) ;
    
    gameService.saveGame(game2) ;
    
    for( int i=0 ; i<game1.getQuestions().size(); i++ ){
    	
    	Question q = new Question(game1.getQuestions().get(i)) ;
    	
    	q.setGame(game2);
    	
    	questionService.saveQuestion(q) ;
    }
    
    gameNotification.notifyGame(course, game2.getName());
    
    mv.addObject("un",username);
    mv.addObject("teacher",teacher);
    
    mv.setViewName("teacher_home");
	
	return mv;
}

@GetMapping("/cancel_game/{username}/{Gameid}")
public ModelAndView CancelGame(@PathVariable String username,@PathVariable Long Gameid){
	
	ModelAndView mv=new ModelAndView();
	
	Teacher t = teacherServices.findByUsername(username) ;
	Game g = gameService.findById(Gameid) ;
	Course c = g.getCourse() ;
	
	g.setcId(c.getCourseid());
	g.setCancelled(true);
	g.setCourse(null);
	
	c.getGames().remove(g) ;
	
	t.getGames().remove(g) ;
	t.getCancelled().add(g) ;
	
	teacherServices.saveTeacher(t) ;
	courseServices.saveCourse(c) ;
	
	mv.addObject("un",username);
    mv.addObject("teacher",t);
    
	mv.setViewName("teacher_home");
	
	return mv ;
}

@GetMapping("/uncancel_game/{username}/{Gameid}")
public ModelAndView UnancelGame(@PathVariable String username,@PathVariable Long Gameid){
	
	ModelAndView mv=new ModelAndView();
	
	Teacher t = teacherServices.findByUsername(username) ;
	Game g = gameService.findById(Gameid) ;
	Course c = courseServices.findById(g.getcId()) ;

	g.setCancelled(false);
	g.setCourse(c);
	
	t.getGames().add(g) ;
	t.getCancelled().remove(g) ;
	
	teacherServices.saveTeacher(t) ;
	gameService.saveGame(g) ;
	
	mv.addObject("un",username);
    mv.addObject("teacher",t);
    mv.addObject("course",c);
    
	mv.setViewName("teacher_home");
	
	return mv ;
}

@PostMapping("/edit_game_options/{username}/{Gameid}")
public ModelAndView EditGame(@ModelAttribute Game game,@PathVariable String username,@PathVariable Long Gameid){
	
    ModelAndView mv=new ModelAndView();
    
    mv.addObject("un",username);
    mv.addObject("gid",Gameid);
    
    mv.setViewName("edit_game_options");
    
	return mv;
}

@PostMapping("/edit_game_info/{username}/{Gameid}")
public ModelAndView EditGameInfo(@PathVariable String username,@PathVariable Long Gameid){
	
    ModelAndView mv=new ModelAndView();
    
    Game g = gameService.findById(Gameid) ;
    
    mv.addObject("un",username);
    mv.addObject("gid",Gameid);
    mv.addObject("game",g);
    
    mv.setViewName("edit_game_info");
    
	return mv;
}

@PostMapping("/save_editing/{username}/{Gameid}")
public ModelAndView SaveEditing(@ModelAttribute Game game ,@PathVariable String username,@PathVariable Long Gameid){
	
    ModelAndView mv=new ModelAndView();
    
    Game g = gameService.findById(Gameid) ;
    
    g.setName(game.getName());
    g.setDescription(game.getDescription());
    
    gameService.saveGame(g) ;
    
    mv.addObject("un",username);
    mv.addObject("gid",Gameid);
    mv.addObject("game",g);
    
    mv.setViewName("edit_game_options");
    
	return mv;
}

@PostMapping("/edit_game_questions/{username}/{Gameid}")
public ModelAndView EditGameQuestions(@PathVariable String username,@PathVariable Long Gameid){
	
    ModelAndView mv=new ModelAndView();
    
    Game g = gameService.findById(Gameid) ;
    
    mv.addObject("un",username);
    mv.addObject("gid",Gameid);
    mv.addObject("game",g);
    
    if( g.getType().equals("MultipleChoice") ){
    	
    	mv.setViewName("edit_game_mcq");
    }
    else{
    	
    	mv.setViewName("edit_game_tf");
    }
   
	return mv;
}

@GetMapping("/delete_question/{username}/{gameId}/{qid}")
public ModelAndView DeleteQuestion(@PathVariable String username,@PathVariable Long gameId,@PathVariable Long qid){
	
	ModelAndView mv=new ModelAndView();
	
	Game g = gameService.findById(gameId) ;
	questionService.deleteById(qid) ;
	gameService.saveGame(g) ;
	
	mv.addObject("un",username);
	mv.addObject("gid",gameId);
	mv.addObject("game",g);
	mv.addObject("cid",g.getCourse().getCourseid()) ;
	
	 if( g.getType().equals("MultipleChoice") ){
	    	
    	mv.setViewName("edit_game_mcq");
    }
    else{
    	
    	mv.setViewName("edit_game_tf");
    }
	
	return mv;
}

@PostMapping("/add_comment/{username}/{Gameid}")
public ModelAndView AddComment(@ModelAttribute Game game ,@PathVariable String username,@PathVariable Long Gameid){
	
    ModelAndView mv=new ModelAndView();
    
    Game g = gameService.findById(Gameid) ;
    
    Comment comment = new Comment() ;
    comment.setContent(game.getLastComment());
    
    comment.setGame1(g);
    
    commentServices.saveComment(comment) ;
    
    commentNotification.notifyComment(g, comment.getContent());
    
    mv.addObject("un",username);
    mv.addObject("gid",Gameid);
    mv.addObject("game",g);
   
	return ViewGame(g,username) ;
}

//	@PostMapping("/display_game")
//	public ModelAndView DisplayGame(@ModelAttribute Game game){
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("game",game) ;
//	
//		return mv ;
//	}
}
