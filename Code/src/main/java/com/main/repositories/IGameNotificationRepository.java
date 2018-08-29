package com.main.repositories;

import org.springframework.data.repository.CrudRepository;

import com.main.model.GameNotification;

public interface IGameNotificationRepository extends CrudRepository<GameNotification,Long>{

}
