package com.twitter.clone.twitterclone.controller;

import com.twitter.clone.twitterclone.entity.Tweet;
import com.twitter.clone.twitterclone.exception.MapValidationErrorService;
import com.twitter.clone.twitterclone.response.RESTApiResponse;
import com.twitter.clone.twitterclone.service.TweetService;
import com.twitter.clone.twitterclone.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("")
    public ResponseEntity<?> saveOrUpdateTweet(@Valid @RequestBody Tweet tweet, BindingResult result, Principal principal) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){

            return RESTApiResponse.responseError(
                    errorMap.getBody(),
                    Status.ERROR,
                    errorMap.getStatusCode().toString(),
                    errorMap.getStatusCodeValue()
            );

        }

        Tweet newTweet = tweetService.saveOrUpdateTweet(tweet, principal.getName());

        return RESTApiResponse.responseSuccess(
                newTweet,
                Status.SUCCESS,
                "Tweet baru berhasil di posting"
        );

    }

    @GetMapping("/me")
    public ResponseEntity<?> getTweetsbyUsername(Pageable pageable, Principal principal) {

        Page tweets = tweetService.getTweetsMe(pageable, principal.getName());

        return RESTApiResponse.responseSuccess(
                tweets,
                Status.SUCCESS,
                "Tweet baru berhasil di posting"
        );

    }

    @GetMapping("/home")
    public ResponseEntity<?> getHomes(Pageable pageable) {

        Page tweets  = tweetService.getHome(pageable);

        return RESTApiResponse.responseSuccess(
                tweets,
                Status.SUCCESS,
                "Tweet baru berhasil di posting"
        );

    }


}
