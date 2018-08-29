package com.main.services;

import com.main.model.Question;

public interface IQuestionServices {
	public boolean saveQuestion(Question question);
	public Question findByGameId(Long id) ;
	public boolean deleteById(Long id) ;
}
