package com.twitter.clone.twitterclone.repository;

import com.twitter.clone.twitterclone.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
