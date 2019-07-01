package com.twitter.clone.twitterclone.service;

import com.twitter.clone.twitterclone.entity.Reply;
import com.twitter.clone.twitterclone.entity.Tweet;
import com.twitter.clone.twitterclone.entity.User;
import com.twitter.clone.twitterclone.exception.ResourceNotFoundException;
import com.twitter.clone.twitterclone.repository.ReplyRepository;
import com.twitter.clone.twitterclone.repository.TweetRepository;
import com.twitter.clone.twitterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TweetRepository tweetRepository;

    public Reply saveOrUpdate(Reply reply,Long tweet_id, String makerName) {

        User maker = userRepository.findByUsername(makerName).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        Tweet tweet = tweetRepository.findById(tweet_id).orElseThrow(() -> new ResourceNotFoundException("Tweet Not Found"));

        reply.setUser(maker);
        reply.setTweet(tweet);

        return replyRepository.save(reply);
    }

    public List<Reply> getRepliesByTweetId(Long tweetId) {
        Tweet tweet = tweetRepository.findById(tweetId).orElseThrow(() -> new ResourceNotFoundException("Tweet Not Found"));

        return replyRepository.findByTweetIdOrderByIdDesc(tweetId);

    }

}
