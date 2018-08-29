package com.main.services;

import java.util.ArrayList;

import com.main.model.Game;

public interface IGameServices {
	
	public boolean saveGame(Game game);
	public ArrayList<Game> getAll();
	public Game findById(Long id) ;
}
