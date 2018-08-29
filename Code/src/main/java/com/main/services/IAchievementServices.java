package com.main.services;

import com.main.model.Achievement;

public interface IAchievementServices {
	
	public boolean saveAchievement(Achievement achievement) ;
	public Achievement find( String studentUsername , Long gameId ) ;
	public boolean isExist( String studentUsername , Long gameId ) ;
	public Achievement updateScore( String studentUsername , Long gameId , int score ) ;
	public Achievement updateMax( String studentUsername , Long gameId ) ;
	public void resetLastScore(Achievement achievement ) ;

}
