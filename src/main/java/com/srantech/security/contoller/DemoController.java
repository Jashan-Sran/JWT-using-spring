package com.srantech.security.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class DemoController { // this class has one end point to check authentication


    @GetMapping("/demo")
    public ResponseEntity<String> demo(){
        return ResponseEntity.ok("Finally I learned JWT ");
    }

}
