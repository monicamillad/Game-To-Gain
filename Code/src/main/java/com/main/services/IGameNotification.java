package com.main.services;

import com.main.model.Game;

public interface IGameNotification {
	
	public void notifyComment( Game game , String comment ) ;
}
