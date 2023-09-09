package com.AbdullahBILGEN.data.repository;

import com.AbdullahBILGEN.business.dto.ToDoDto;
import com.AbdullahBILGEN.data.entity.ToDoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IToDoRepository extends CrudRepository<ToDoEntity,Long> {

    static void todoServiceUpdateTodoComplete(Long id, ToDoDto.TodoStatus newTodoComplete) {
    }

    // Delivered Query
    Optional<ToDoEntity> findByTodoContent(String categoryName);

    List<ToDoEntity> findByTodoComplete(ToDoDto.TodoStatus todoStatus);
}
