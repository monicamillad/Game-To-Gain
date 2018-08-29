package com.main.services;

import java.util.ArrayList;

import com.main.model.CommentNotification;

public interface ICommentNotificationServices {

	public boolean saveNotification( CommentNotification notification ) ;
	public ArrayList<CommentNotification> getAll() ;
}
