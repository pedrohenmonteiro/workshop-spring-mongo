package com.pedrom.workshopmongo.resources;

import com.pedrom.workshopmongo.domain.Post;
import com.pedrom.workshopmongo.domain.User;
import com.pedrom.workshopmongo.dto.UserDTO;
import com.pedrom.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        User u1 = new User("1", "Maria Brown", "maria@gmail.com");
        User u2 = new User("2", "Alex Green", "alex@gmail.com");

        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {

        User obj = service.findById(id);

        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @PostMapping
    public ResponseEntity<User> inser(@RequestBody UserDTO objDto) {

        User obj = service.fromDTO(objDto);
        obj = service.insert(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody UserDTO objDto) {
    User obj = service.fromDTO(objDto);
    obj.setId(id);
    obj = service.update(obj);
    return ResponseEntity.ok().body(obj);
    }


    @GetMapping(value="/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {

        User obj = service.findById(id);

        return ResponseEntity.ok().body(obj.getPost());
    }



}
