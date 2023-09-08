package com.AbdullahBILGEN.data.entity;

import com.AbdullahBILGEN.auditing.AuditingAwareBaseEntity;
import com.AbdullahBILGEN.business.dto.ToDoDto.TodoStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

// LOMBOK
@Data
@Log4j2

// ENTITY
@Entity
@Table(name = "todoDB")
// Category(1) Blog(N)
public class ToDoEntity extends AuditingAwareBaseEntity implements Serializable {

    // serileştirme
    public static final Long serialVersionUID = 1L;

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="todo_id",unique = true,nullable = false,insertable = true,updatable = false)
    private Long todoId;

    // TODO CONTENT
    @Column(name = "todo_content")
    private String todoContent;

    @Column(name = "todo_complete")
    @Enumerated(EnumType.ORDINAL) // Store enum values as integers in the database
    private TodoStatus todoComplete;

    // DATE
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date systemDate;


    // Constructor (parametresiz)
    public ToDoEntity() {
        this.todoComplete = TodoStatus.INCOMPLETE;
    }

    // Constructor (parametreli)
    public ToDoEntity(String todoContent) {
        this.todoContent = todoContent;
        this.todoComplete = TodoStatus.INCOMPLETE;
    }


} //end class
