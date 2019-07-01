package com.twitter.clone.twitterclone.controller;

import com.twitter.clone.twitterclone.entity.Favorite;
import com.twitter.clone.twitterclone.response.RESTApiResponse;
import com.twitter.clone.twitterclone.service.FavoriteService;
import com.twitter.clone.twitterclone.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/set/{tweet_id}")
    public ResponseEntity<?> setFavorite(@PathVariable("tweet_id") Long tweetId, Principal principal) {

        Favorite newFavorite = favoriteService.setFavorite(tweetId, principal.getName());

        return RESTApiResponse.responseSuccess(
                newFavorite,
                Status.SUCCESS,
                "New Tweet Favorite"
        );

    }


}
