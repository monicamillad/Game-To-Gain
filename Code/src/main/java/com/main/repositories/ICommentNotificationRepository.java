package com.main.repositories;

import org.springframework.data.repository.CrudRepository;

import com.main.model.CommentNotification;

public interface ICommentNotificationRepository extends CrudRepository<CommentNotification,Long>{

}
