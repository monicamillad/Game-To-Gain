package com.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Course;
import com.main.model.GameNotification;
import com.main.services.IGameNotificationServices;

@Service
public class CourseNotification implements ICourseNotification{

	@Autowired
	IGameNotificationServices notificationService ;
	
	@Autowired
	IStudentServices studentService ;
	
	@Override
	public void notifyGame(Course c, String g) {
		
		for( int i=0 ; i<c.getStudents().size() ; i++ ){
		
			GameNotification n = new GameNotification() ;
			
			n.setNotif("Game "+ g + " has been added to Course " + c.getName() );
			
			n.setS(c.getStudents().get(i));
			
			notificationService.saveNotification(n) ;
			
			studentService.saveStudent(c.getStudents().get(i)) ;
		}
		
	}

}
