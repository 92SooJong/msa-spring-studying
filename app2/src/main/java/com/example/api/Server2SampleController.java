package com.example.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Server2SampleController {


    @GetMapping("/api/server2")
    public String getServer2() {
        return "Hello from Server 2!";
    }


}
