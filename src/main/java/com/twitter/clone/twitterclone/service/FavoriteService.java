package com.twitter.clone.twitterclone.service;

import com.twitter.clone.twitterclone.entity.Favorite;
import com.twitter.clone.twitterclone.entity.Tweet;
import com.twitter.clone.twitterclone.entity.User;
import com.twitter.clone.twitterclone.exception.ResourceNotFoundException;
import com.twitter.clone.twitterclone.repository.FavoriteRepository;
import com.twitter.clone.twitterclone.repository.TweetRepository;
import com.twitter.clone.twitterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    public Favorite setFavorite(Long tweetId, String makerName) {

        User maker = userRepository.findByUsername(makerName).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new ResourceNotFoundException("Tweet Not Found"));

        return favoriteRepository.save(new Favorite(tweet, maker));

    }

}
