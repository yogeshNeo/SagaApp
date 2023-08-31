package com.ai.repository.mongo;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.ai.domain.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
