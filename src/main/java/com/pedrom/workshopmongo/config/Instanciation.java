package com.pedrom.workshopmongo.config;

import com.pedrom.workshopmongo.domain.Post;
import com.pedrom.workshopmongo.domain.User;
import com.pedrom.workshopmongo.dto.AuthorDTO;
import com.pedrom.workshopmongo.repository.PostRepository;
import com.pedrom.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;


@Configuration
public class Instanciation implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {


        userRepository.deleteAll();
        postRepository.deleteAll();


        User u1 = new User(null, "Maria Brown", "maria@gmail.com");
        User u2 = new User(null, "Alex Green", "alex@gmail.com");
        User u3 = new User(null, "Bob Grey", "bob@gmail.com");
        userRepository.saveAll(Arrays.asList(u1,u2,u3));


        Post p1 = new Post(null, Instant.now(), "Bom dia", "Hoje foi um lindo dia", new AuthorDTO(u1));
        Post p2 = new Post(null, Instant.now(), "Partiu viagem", "Vou para Ubatuba, uhuu!", new AuthorDTO(u1));

        postRepository.saveAll(Arrays.asList(p1,p2));

        u1.getPost().addAll(Arrays.asList(p1, p2));
        userRepository.save(u1);
    }
}
