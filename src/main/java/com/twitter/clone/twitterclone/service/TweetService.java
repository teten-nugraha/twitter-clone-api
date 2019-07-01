package com.twitter.clone.twitterclone.service;

import com.twitter.clone.twitterclone.entity.Tweet;
import com.twitter.clone.twitterclone.entity.User;
import com.twitter.clone.twitterclone.exception.ResourceNotFoundException;
import com.twitter.clone.twitterclone.repository.TweetRepository;
import com.twitter.clone.twitterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    public Tweet saveOrUpdateTweet(Tweet tweet, Long makerId) {

        User maker = userRepository.getById(makerId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        tweet.setUser(maker);
        return tweetRepository.save(tweet);


    }


}
