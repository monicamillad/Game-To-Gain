package com.main.repositories;

import org.springframework.data.repository.CrudRepository;

import com.main.model.Question;


public interface IQuestionRepository extends CrudRepository<Question,Long> {

}
