package com.main.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.GameNotification;
import com.main.repositories.IGameNotificationRepository;

@Service
public class GameNotificationServices implements IGameNotificationServices{
	
	@Autowired
	IGameNotificationRepository repository ;

	@Override
	public boolean saveNotification(GameNotification nontification) {

		if(repository.save(nontification) != null)
			return true;
		return false;
	}

	@Override
	public ArrayList<GameNotification> getAll() {
		
		Iterable<GameNotification> notificationsIterator = repository.findAll();
		ArrayList<GameNotification> allNotifications = new ArrayList<GameNotification>() ;
		for( GameNotification notification : notificationsIterator ){
			allNotifications.add(notification) ;
		}
		return allNotifications;
	}

}
