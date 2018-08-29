package com.main.services;

import java.util.ArrayList;

import com.main.model.GameNotification;

public interface IGameNotificationServices {
	
	public boolean saveNotification(GameNotification nontification) ;
	public ArrayList<GameNotification> getAll() ;

}
