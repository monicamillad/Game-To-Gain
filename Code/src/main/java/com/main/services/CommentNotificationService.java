package com.main.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.CommentNotification;
import com.main.repositories.ICommentNotificationRepository;

@Service
public class CommentNotificationService implements ICommentNotificationServices {
	
	@Autowired
	ICommentNotificationRepository repository ;

	@Override
	public boolean saveNotification(CommentNotification notification) {

		if(repository.save(notification) != null)
			return true;
		return false;
	}

	@Override
	public ArrayList<CommentNotification> getAll(){

		Iterable<CommentNotification> notificationsIterator = repository.findAll(); 
		ArrayList<CommentNotification> allNotifications = new ArrayList<CommentNotification>() ;
		for( CommentNotification notification : notificationsIterator ){
			allNotifications.add(notification) ;
		}
		
		return allNotifications;
	}

}
