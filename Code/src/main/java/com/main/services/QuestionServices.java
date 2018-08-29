package com.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Question;
import com.main.repositories.IQuestionRepository;

@Service
public class QuestionServices  implements IQuestionServices {
	
	@Autowired
	IQuestionRepository repository;


	@Override
	public boolean saveQuestion(Question question) {
		if(repository.save(question) != null)
			return true;
		return false;
	}


	@Override
	public Question findByGameId(Long id) {
		
		Iterable<Question> questionsIterator = repository.findAll();
		for( Question question : questionsIterator ){
			
			if( question.getQid()==id ){
				
				return question ;
			}
		}
		
		return null;
	}

	@Override
	public boolean deleteById(Long id) {
		
		Iterable<Question> questionsIterator = repository.findAll();
		for( Question question : questionsIterator ){
			
			if( question.getQid()==id ){
				
				repository.delete(question);
				return true ;
			}
		}
		return false;
	}
}
