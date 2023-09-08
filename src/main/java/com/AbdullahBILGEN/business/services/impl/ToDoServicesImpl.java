package com.AbdullahBILGEN.business.services.impl;

import com.AbdullahBILGEN.bean.ModelMapperBean;
import com.AbdullahBILGEN.business.dto.ToDoDto;
import com.AbdullahBILGEN.business.services.IToDoServices;
import com.AbdullahBILGEN.data.entity.ToDoEntity;
import com.AbdullahBILGEN.data.repository.IToDoRepository;
import com.AbdullahBILGEN.exception.AbdullahBILGENException;
import com.AbdullahBILGEN.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// LOMBOK
@RequiredArgsConstructor
@Log4j2

// SERVICES
@Service
public class ToDoServicesImpl implements IToDoServices<ToDoDto, ToDoEntity> {

    // Injection (Field) => 1.YOL
    /*
    @Autowired
    private ICategoryRepository iCategoryRepository;
    */

    // Injection (Constructor Field) => 2.YOL
    /*
    private final ICategoryRepository iCategoryRepository;
    @Autowired
    public CategoryServicesImpl(ICategoryRepository iCategoryRepository) {
        this.iCategoryRepository = iCategoryRepository;
    }
    */

    // Injection (Lombok Constructor Field) => 3.YOL
    private final IToDoRepository IToDoRepository;
    private final ModelMapperBean modelMapperBean;


    // MODEL MAPPER
    @Override
    public ToDoDto entityToDto(ToDoEntity toDoEntity) {
        return modelMapperBean.modelMapperMethod().map(toDoEntity, ToDoDto.class);
    }

    @Override
    public ToDoEntity dtoToEntity(ToDoDto toDoDto) {
        return  modelMapperBean.modelMapperMethod().map(toDoDto, ToDoEntity.class);
    }

    // CREATE
    @Override
    @Transactional // create, delete, update
    public ToDoDto todoServiceCreate(ToDoDto toDoDto) {
        if(toDoDto !=null){
            // Set todoComplete to INCOMPLETE for new entries
            toDoDto.setTodoComplete(ToDoDto.TodoStatus.INCOMPLETE);
            ToDoEntity toDoEntity =dtoToEntity(toDoDto);
            IToDoRepository.save(toDoEntity);
            toDoDto.setId(toDoEntity.getTodoId());
            toDoDto.setSystemDate(toDoEntity.getSystemDate());
        }else{
            throw  new NullPointerException( " ToDoDto null veri");
        }
        return toDoDto;
    }

    // LIST
    @Override
    public List<ToDoDto> todoServiceList() {
        Iterable<ToDoEntity> entityIterable=  IToDoRepository.findAll();
        // Dto To entityb List
        List<ToDoDto> toDoDtoList =new ArrayList<>();
        for (ToDoEntity entity:  entityIterable) {
            ToDoDto toDoDto =entityToDto(entity);
            toDoDtoList.add(toDoDto);
        }
        log.info("Liste Sayısı: "+ toDoDtoList.size());
        return toDoDtoList;
    }

    // FIND
    @Override
    public ToDoDto todoServiceFindById(Long id) {
        // 1.YOL (FIND)
        /*
        Optional<CategoryEntity> findOptionalCategoryEntity=  iCategoryRepository.findById(id);
        ToDoDto categoryDto=entityToDto(findOptionalCategoryEntity.get());
        if(findOptionalCategoryEntity.isPresent()){
            return categoryDto;
        }
        */

        // 2.YOL (FIND)
        ToDoEntity findToDoEntity =  null;
        if(id!=null){
            findToDoEntity =  IToDoRepository.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException(id+" nolu id yoktur"));
        }else {
            throw new AbdullahBILGENException("İd null olarak geldi");
        }
        return entityToDto(findToDoEntity);
    }

    // UPDATE
    @Override
    @Transactional
    public ToDoDto todoServiceUpdate(Long id, ToDoDto toDoDto) {
        // Retrieve the existing record
        Optional<ToDoEntity> todoEntityOptional = IToDoRepository.findById(id);

        if (todoEntityOptional.isPresent()) {
            ToDoEntity toDoEntity = todoEntityOptional.get();

            // Update the fields with new values
            toDoEntity.setTodoContent(toDoDto.getTodoContent());

            // Save the updated entity
            ToDoEntity updatedEntity = IToDoRepository.save(toDoEntity);

            // Convert the updated entity back to DTO
            return entityToDto(updatedEntity);
        } else {
            return null;
        }
    }


    // UPDATE TODO COMPLETE
    @Override
    @Transactional
    public ToDoDto todoServiceUpdateTodoComplete(Long id, ToDoDto.TodoStatus newTodoComplete) {
        Optional<ToDoEntity> todoEntityOptional = IToDoRepository.findById(id);

        if (todoEntityOptional.isPresent()) {
            ToDoEntity toDoEntity = todoEntityOptional.get();
            toDoEntity.setTodoComplete(newTodoComplete);

            // You don't need to convert DTO to Entity again here, as you already have the entity
            IToDoRepository.save(toDoEntity);

            // Convert the updated entity back to DTO
            return entityToDto(toDoEntity);
        } else {
            // Handle the case where the record with the given ID is not found
            // You can throw an exception or return an appropriate response
            return null;
        }
    }



    // DELETE
    @Override
    @Transactional // create, delete, update
    public ToDoDto todoServiceDeleteById(Long id) {
        // Önce Bul
        ToDoDto categoryFindDto= todoServiceFindById(id);
        if(categoryFindDto!=null){
            IToDoRepository.deleteById(id);
            // Dönüştede ID ve Date Set et
        }
        return categoryFindDto;
    }

    // DELETE ALL
    @Override
    public void todoServiceDeleteAll() {
        IToDoRepository.deleteAll(); // Assuming you have a JpaRepository for ToDoEntity
    }

    // DELETE COMPLETED
    @Override
    public void deleteCompletedToDoItems() {
            List<ToDoEntity> completedItems = IToDoRepository.findByTodoComplete(ToDoDto.TodoStatus.COMPLETE);
        IToDoRepository.deleteAll(completedItems);
    }



} //end class
