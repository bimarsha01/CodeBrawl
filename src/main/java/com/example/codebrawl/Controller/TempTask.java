package com.example.codebrawl.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/tasks")
    public class TempTask {

        @GetMapping
        public String test() {
            return "Hello from protected endpoint";
        }
    }

