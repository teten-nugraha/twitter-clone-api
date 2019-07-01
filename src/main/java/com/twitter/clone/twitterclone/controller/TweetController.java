package com.twitter.clone.twitterclone.controller;

import com.twitter.clone.twitterclone.entity.Tweet;
import com.twitter.clone.twitterclone.exception.MapValidationErrorService;
import com.twitter.clone.twitterclone.response.RESTApiResponse;
import com.twitter.clone.twitterclone.service.TweetService;
import com.twitter.clone.twitterclone.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

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

}
