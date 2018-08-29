package com.main.repositories;

import org.springframework.data.repository.CrudRepository;

import com.main.model.Teacher;

public interface ITeacherRepository extends CrudRepository<Teacher, String> {

}
