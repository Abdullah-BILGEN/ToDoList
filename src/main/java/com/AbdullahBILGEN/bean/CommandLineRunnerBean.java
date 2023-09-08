package com.AbdullahBILGEN.bean;

import com.AbdullahBILGEN.business.services.IToDoServices;
import com.AbdullahBILGEN.data.entity.ToDoEntity;
import com.AbdullahBILGEN.data.repository.IToDoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.UUID;

// Lombok
@RequiredArgsConstructor

@Configuration
@Log4j2
@Component
public class CommandLineRunnerBean {


    // Injection
    private final IToDoServices iToDoServices;
    private final IToDoRepository iToDoRepository;


    // CategoryName (Save)
    public ToDoEntity toDoEntitySave(String todoContent) {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setTodoContent(todoContent);
        iToDoRepository.save(toDoEntity);
        return toDoEntity;
    }

    // Random Category
    public String[] randomCategory() {
        String[] randomData = new String[5];
        randomData[0] = "bilgisayar"+ UUID.randomUUID().toString();
        randomData[1] = "laptop"+ UUID.randomUUID().toString();
        randomData[2] = "mac"+ UUID.randomUUID().toString();
        randomData[3] = "pc"+ UUID.randomUUID().toString();
        randomData[4] = "car"+ UUID.randomUUID().toString();
        // döngüde rastgele bir tane category seçecek
        for (int i = 0; i < 5; i++) {
            toDoEntitySave(randomData[i]);
        }
        // döngüde rastgele bir tane category seçecek
        for (int i = 0; i < randomData.length; i++) {
            System.out.println(randomData[i]);
        }
        return randomData;
    }


    @Bean
    public CommandLineRunner blogCommandLineRunnerMethod() {
        return args -> {
            System.out.println("CommandLineRunner Çalıştı");
            log.info("CommandLineRunner Çalıştı");

            //randomCategory();
        };
    }
}