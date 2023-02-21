package com.pedrom.workshopmongo.services;

import com.pedrom.workshopmongo.domain.Post;
import com.pedrom.workshopmongo.domain.User;
import com.pedrom.workshopmongo.dto.UserDTO;
import com.pedrom.workshopmongo.repository.PostRepository;
import com.pedrom.workshopmongo.repository.UserRepository;
import com.pedrom.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repo;

    public Post findById(String id) {
        Optional<Post> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found"));
    }

    public List<Post> findByTitle (String text) {
    return repo.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return repo.fullSearch(text, minDate, maxDate);
    }
}
