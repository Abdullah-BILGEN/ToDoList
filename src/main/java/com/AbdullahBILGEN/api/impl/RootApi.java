package com.AbdullahBILGEN.api.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/root")
public class RootApi {

    // http://localhost:4444/root/index
    @GetMapping({"/","/index"})
    public ResponseEntity<String> getRoot(){
        return ResponseEntity.ok("index");
    }

}
