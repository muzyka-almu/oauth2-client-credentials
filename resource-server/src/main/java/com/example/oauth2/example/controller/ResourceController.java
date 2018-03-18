package com.example.oauth2.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
public class ResourceController {
    private String message = "Hello world!";

    // @PreAuthorize("hasRole('ROLE_RS_READ')")
    @PreAuthorize("#oauth2.hasScope('resource-server-read')")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Map<String, String> home() {
        return Collections.singletonMap("message", message);
    }

    // @PreAuthorize("hasRole('ROLE_RS_WRITE')")
    @PreAuthorize("#oauth2.hasScope('resource-server-write')")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void updateMessage(@RequestBody String message) {
        this.message = message;
    }

    // TODO issue with #oauth2.hasScope - https://github.com/spring-projects/spring-boot/issues/5096
    @PreAuthorize("#oauth2.hasScope('resource-server-read')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Map<String, String> user(Principal user) {
        return Collections.singletonMap("message", "user is: " + user.toString());
    }
}
