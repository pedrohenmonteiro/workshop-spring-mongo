package com.pedrom.workshopmongo.resources;

import com.pedrom.workshopmongo.domain.User;
import com.pedrom.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User u1 = new User("1", "Maria Brown", "maria@gmail.com");
        User u2 = new User("2", "Alex Green", "alex@gmail.com");

        List list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
