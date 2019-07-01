package com.twitter.clone.twitterclone.service;

import com.twitter.clone.twitterclone.entity.Tweet;
import com.twitter.clone.twitterclone.entity.User;
import com.twitter.clone.twitterclone.exception.ResourceNotFoundException;
import com.twitter.clone.twitterclone.repository.TweetRepository;
import com.twitter.clone.twitterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    public Tweet saveOrUpdateTweet(Tweet tweet, String makerName) {

        User maker = userRepository.findByUsername(makerName).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        tweet.setUser(maker);
        return tweetRepository.save(tweet);


    }

    public Page<Tweet> getTweetsMe(Pageable pageable, String makername) {

        User maker = userRepository.findByUsername(makername).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        return tweetRepository.findAllByUserId(maker.getId(), pageable);

    }


}
