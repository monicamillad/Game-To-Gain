package com.main.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Game;
import com.main.repositories.IGameRepository;

@Service
public class GameServices implements IGameServices {
	
	@Autowired
	IGameRepository repository;
	
	@Override
	public boolean saveGame(Game game) {
		if(repository.save(game) != null)
			return true;
		return false;
	}

	@Override
	public ArrayList<Game> getAll() {
		
		Iterable<Game> gamesIterator = repository.findAll();
		ArrayList<Game> allGames = new ArrayList<Game>();
		for(Game game : gamesIterator){

			allGames.add(game);
		}
		return allGames;
	}

	@Override
	public Game findById(Long id) {
		
		Iterable<Game> gamesIterator = repository.findAll();
		for(Game game : gamesIterator){

			if(game.getId()==id){
				return game ;
			}
		}
		return null;
	}

	
}
