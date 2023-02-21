package com.pedrom.workshopmongo.repository;

import com.pedrom.workshopmongo.domain.Post;
import com.pedrom.workshopmongo.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

}
