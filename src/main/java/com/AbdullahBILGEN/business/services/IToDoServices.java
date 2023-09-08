package com.AbdullahBILGEN.business.services;

import com.AbdullahBILGEN.business.dto.ToDoDto;

import java.util.List;

// D: Dto
// E: Entity
public interface IToDoServices<D, E> {

    // Model Mapper
    public D entityToDto(E e);

    public E dtoToEntity(D d);

    // C R U D
    // CREATE
    public D todoServiceCreate(D d);

    // LIST
    public List<D> todoServiceList();

    // FIND BY
    public D todoServiceFindById(Long id);

    // UPDATE
    public D todoServiceUpdate(Long id,D d);

    // UPDATE TODO COMPLETE
    ToDoDto todoServiceUpdateTodoComplete(Long id, ToDoDto.TodoStatus newTodoComplete);

    // DELETE
    public D todoServiceDeleteById(Long id);

    // DELETE ALL
    void todoServiceDeleteAll();

    // DELETE COMPLETED
    void deleteCompletedToDoItems();
}
