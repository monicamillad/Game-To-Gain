package com.main.repositories;

import org.springframework.data.repository.CrudRepository;

import com.main.model.Comment;

public interface ICommentRepository extends CrudRepository<Comment,Long>{

}
