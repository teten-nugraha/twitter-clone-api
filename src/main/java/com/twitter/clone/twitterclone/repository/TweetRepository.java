package com.twitter.clone.twitterclone.repository;

import com.twitter.clone.twitterclone.entity.Tweet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

    Page<Tweet> findAllByUserId(Long id,Pageable pageable);
    Page<Tweet> findAll(Pageable pageable);


}
