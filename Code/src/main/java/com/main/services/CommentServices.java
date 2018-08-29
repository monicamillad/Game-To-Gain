package com.main.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.model.Comment;
import com.main.repositories.ICommentRepository;

@Service
public class CommentServices implements ICommentServices {

	@Autowired
	ICommentRepository repository ;
	
	@Override
	public boolean saveComment(Comment comment) {
		
		if(repository.save(comment) != null)
			return true;
		return false;
	}

	@Override
	public ArrayList<Comment> getAll() {
		
		Iterable<Comment> commentsIterator = repository.findAll();
		ArrayList<Comment> allComments = new ArrayList<Comment>() ;
		for( Comment comment : commentsIterator ){
			allComments.add(comment) ;
		}
		return allComments;
	}

}
