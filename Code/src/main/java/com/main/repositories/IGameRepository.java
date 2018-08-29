package com.main.repositories;

import org.springframework.data.repository.CrudRepository;

import com.main.model.Game;


public interface IGameRepository extends CrudRepository<Game,Long> {

}
