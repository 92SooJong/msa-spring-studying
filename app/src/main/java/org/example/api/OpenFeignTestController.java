package org.example.api;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.client.Server2Client;

@RestController
@RequestMapping("/api/openfeign")
public class OpenFeignTestController {

    private final Server2Client server2Client;

    public OpenFeignTestController(Server2Client server2Client) {
        this.server2Client = server2Client;
    }

    @GetMapping("/get-data-from-server2")
    public String getDataFromServer2() {

        return server2Client.getServer2();
    }

}
