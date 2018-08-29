package com.main.services;

import java.util.ArrayList;

import com.main.model.Comment;

public interface ICommentServices {

	public boolean saveComment( Comment comment ) ;
	public ArrayList<Comment> getAll() ;
}
