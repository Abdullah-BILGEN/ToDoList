package com.AbdullahBILGEN.controller.api;

import com.AbdullahBILGEN.business.dto.ToDoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface IToDoApi<D> {

    // C R U D
    // CREATE
    public ResponseEntity<?>  todoApiCreate(D d);

    // LIST
    public ResponseEntity<List<D>>  todoApiList();

    // FIND BY
    public ResponseEntity<?>  todoApiFindById(Long id);

    // UPDATE
    public ResponseEntity<?>  todoApiUpdate(Long id,D d);

    // UPDATE STATUS
    public ResponseEntity<?> todoApiUpdateStatus(Long id, ToDoDto.TodoStatus newTodoComplete);

    // UPDATE STATUS
// http://localhost:4444/todo/api/v1/updateStatus/1
    @PutMapping(value = "/updateStatus/{id}")
    ResponseEntity<?> todoApiUpdateStatus(@PathVariable(name = "id") Long id, @RequestBody Map<String, String> requestBody);

    // DELETE
    public ResponseEntity<?>  todoApiDeleteById(Long id);

    //////////////////////////////////////
    // ALL DELETE
    public ResponseEntity<String> todoApiAllDelete();


    // DELETE COMPLETED
    public ResponseEntity<String> deleteCompletedToDoItems();

    // SPEED DATA
    public ResponseEntity<List<D>> todoApiSpeedData(Long key);
}
