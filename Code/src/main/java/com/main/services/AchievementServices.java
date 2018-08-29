package com.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Achievement;
import com.main.repositories.IAchievementRepository;

@Service
public class AchievementServices implements IAchievementServices{

	@Autowired
	IAchievementRepository repository ;
	
	@Override
	public boolean saveAchievement(Achievement achievement) {
		
		if(repository.save(achievement) != null)
			return true;
		return false;
	}
	
	@Override
	public Achievement find(String studentUsername, Long gameId) {
		
		Iterable<Achievement>  achievementsIterator = repository.findAll();
		for(Achievement achievement : achievementsIterator){

			if( achievement.getStudent().getUsername().equals(studentUsername) && achievement.getGameId()==gameId ){
				return achievement ;
			}
		}
		return null;
	}

	@Override
	public boolean isExist(String studentUsername, Long gameId) {
		Iterable<Achievement>  achievementsIterator = repository.findAll();
		for(Achievement achievement : achievementsIterator){

			if( achievement.getStudent().getUsername().equals(studentUsername) && achievement.getGameId()==gameId ){
				return true ;
			}
		}
		return false;
	}

	@Override
	public Achievement updateScore(String studentUsername , Long gameId , int score) {
		
		Achievement achievement = new Achievement() ;
		achievement = find( studentUsername, gameId) ;
		repository.delete(achievement);
		achievement.setLastScore(achievement.getLastScore() + score);
		repository.save(achievement) ;
		return achievement ;
		
	}

	@Override
	public Achievement updateMax(String studentUsername, Long gameId ) {
		
		Achievement achievement = new Achievement() ;
		achievement = find( studentUsername, gameId) ;
		
		if( achievement.getLastScore()>achievement.getMaxScore() ){
			
			achievement.setMaxScore(achievement.getLastScore());
		}
		
		return achievement ;
		
	}

	@Override
	public void resetLastScore(Achievement achievement) {
		
		repository.delete(achievement);
		achievement.setLastScore(0);
		repository.save(achievement) ;
		
	}

}
