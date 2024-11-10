package org.example.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "server2Client", url = "http://localhost:8081")
public interface Server2Client {

    @GetMapping("/api/server2")
    String getServer2();

}
