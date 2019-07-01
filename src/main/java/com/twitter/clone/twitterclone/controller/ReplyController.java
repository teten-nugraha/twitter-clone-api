package com.twitter.clone.twitterclone.controller;

import com.twitter.clone.twitterclone.entity.Reply;
import com.twitter.clone.twitterclone.exception.MapValidationErrorService;
import com.twitter.clone.twitterclone.response.RESTApiResponse;
import com.twitter.clone.twitterclone.service.ReplyService;
import com.twitter.clone.twitterclone.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/replies")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{tweet_id}")
    public ResponseEntity<?> saveOrUpdateReplies(@PathVariable("tweet_id") Long tweet_id, @Valid @RequestBody Reply reply, Principal principal, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null){

            return RESTApiResponse.responseError(
                    errorMap.getBody(),
                    Status.ERROR,
                    errorMap.getStatusCode().toString(),
                    errorMap.getStatusCodeValue()
            );

        }

        Reply newReply = replyService.saveOrUpdate(reply,tweet_id,principal.getName());

        return RESTApiResponse.responseSuccess(
                newReply,
                Status.SUCCESS,
                "Reply Anda berhasil di posting"
        );

    }

    @GetMapping("/{tweet_id}")
    public ResponseEntity<?> getRepliesByTweetid(@PathVariable("tweet_id") Long id) {

        return RESTApiResponse.responseSuccess(
                replyService.getRepliesByTweetId(id),
                Status.SUCCESS,
                ""
        );

    }
}
