package com.AbdullahBILGEN.api.impl;

import com.AbdullahBILGEN.assist.FrontendUrl;
import com.AbdullahBILGEN.business.dto.ToDoDto;
import com.AbdullahBILGEN.business.services.IToDoServices;
import com.AbdullahBILGEN.controller.api.IToDoApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// API
@RestController
@CrossOrigin(origins = FrontendUrl.REACT_URL) // http://localhost:3000
@RequestMapping("/todo/api/v1")
public class ToDoApiImpl implements IToDoApi<ToDoDto> {

    // Injection
    private final IToDoServices iToDoServices;

    // CREATE
    // http://localhost:4444/todo/api/v1/create
    @Override
    @PostMapping("/create")
    public ResponseEntity<?> todoApiCreate(@Valid @RequestBody ToDoDto toDoDto) {
        return ResponseEntity.ok(iToDoServices.todoServiceCreate(toDoDto));
    }

    // LIST
    // http://localhost:4444/todo/api/v1/list
    @Override
    @GetMapping(value="/list")
    public ResponseEntity<List<ToDoDto>> todoApiList() {
        return ResponseEntity.status(HttpStatus.OK).body(iToDoServices.todoServiceList());
    }

    // FIND
    // http://localhost:4444/todo/api/v1/find/1
    @Override
    @GetMapping(value="/find/{id}")
    public ResponseEntity<?> todoApiFindById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(200).body(iToDoServices.todoServiceFindById(id));
    }

    // UPDATE
    // http://localhost:4444/todo/api/v1/update/1
    @Override
    @PutMapping(value="/update/{id}")
    public ResponseEntity<?> todoApiUpdate(@PathVariable(name = "id") Long id, @Valid @RequestBody ToDoDto toDoDto) {
        return ResponseEntity.ok().body(iToDoServices.todoServiceUpdate(id, toDoDto));
    }

    @Override
    public ResponseEntity<?> todoApiUpdateStatus(Long id, ToDoDto.TodoStatus newTodoComplete) {
        return null;
    }

    // UPDATE STATUS
    // http://localhost:4444/todo/api/v1/updateStatus/1
    @Override
    @PutMapping(value = "/updateStatus/{id}")
    public ResponseEntity<?> todoApiUpdateStatus(@PathVariable(name = "id") Long id, @RequestBody Map<String, String> requestBody) {
        String newTodoCompleteValue = requestBody.get("todoComplete");

        // Check if the "todoComplete" field is present and contains a valid value
        if (newTodoCompleteValue != null && (newTodoCompleteValue.equals("INCOMPLETE") || newTodoCompleteValue.equals("COMPLETE"))) {
            // Parse the string value into a TodoStatus enum
            ToDoDto.TodoStatus newTodoComplete = ToDoDto.TodoStatus.valueOf(newTodoCompleteValue);

            // Call the service method to update the status
            ToDoDto updatedTodo = iToDoServices.todoServiceUpdateTodoComplete(id, newTodoComplete);

            if (updatedTodo != null) {
                return ResponseEntity.ok().body(updatedTodo);
            } else {
                return ResponseEntity.notFound().build(); // Handle not found case
            }
        } else {
            return ResponseEntity.badRequest().body("Invalid 'todoComplete' value."); // Handle invalid input
        }
    }



    // DELETE BY ID
    // http://localhost:4444/todo/api/v1/delete/1
    @Override
    @DeleteMapping(value="/delete/{id}")
    public ResponseEntity<?> todoApiDeleteById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(iToDoServices.todoServiceDeleteById(id),HttpStatus.OK);
    }

    ///////////////////////////////////////////////////////
    // ALL DELETE
    // http://localhost:4444/todo/api/v1/delete/all
    @Override
    @DeleteMapping("/delete/all")
    public ResponseEntity<String> todoApiAllDelete() {
        iToDoServices.todoServiceDeleteAll();
        return ResponseEntity.ok("All data deleted successfully.");
    }

    // DELETE COMPLETED
    // http://localhost:4444/todo/api/v1/delete/completed
    @Override
    @DeleteMapping("/delete/completed")
    public ResponseEntity<String> deleteCompletedToDoItems() {
        iToDoServices.deleteCompletedToDoItems();
        return ResponseEntity.ok("Completed ToDo items deleted successfully.");
    }

    // SPEED DATA
    @Override
    public ResponseEntity<List<ToDoDto>> todoApiSpeedData(Long key) {
        return null;
    }

} //end class
