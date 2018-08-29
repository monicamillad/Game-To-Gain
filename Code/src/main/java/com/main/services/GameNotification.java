package com.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.CommentNotification;
import com.main.model.Game;

@Service
public class GameNotification implements IGameNotification{
	
	@Autowired
	ICommentNotificationServices notificationService ;
	
	@Autowired
	ITeacherServices teacherService ;

	@Override
	public void notifyComment(Game game, String comment) {

		String s = "Someone Commented on Game " + game.getName() + " : \"" + comment + " \"" ;
		
		for( int i=0 ; i<game.getCollaborators().size() ; i++ ){
			
			CommentNotification n = new CommentNotification() ;
			
			n.setCommentNotif(s);
			
			n.setT(game.getCollaborators().get(i)) ;
			
			notificationService.saveNotification(n) ;
			
			teacherService.saveTeacher(game.getCollaborators().get(i)) ;
		}
		
	}

}
