package com.example.oauth2.example.controller;

import com.example.oauth2.example.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AppController {
    @Autowired
    private OAuth2RestTemplate resourceServerProxy;

    @RequestMapping(value = "/api/message", method = RequestMethod.GET)
    public Map<String, String> getMessage() {
        return resourceServerProxy.getForObject("http://localhost:9090", Map.class);
    }

    @RequestMapping(value = "/api/message", method = RequestMethod.POST)
    public void saveMessage(@RequestBody String newMessage) {
        resourceServerProxy.postForLocation("http://localhost:9090", newMessage);
    }
}
