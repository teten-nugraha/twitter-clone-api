package com.twitter.clone.twitterclone.service;

import com.twitter.clone.twitterclone.entity.Reply;
import com.twitter.clone.twitterclone.entity.User;
import com.twitter.clone.twitterclone.exception.ResourceNotFoundException;
import com.twitter.clone.twitterclone.repository.ReplyRepository;
import com.twitter.clone.twitterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    public Reply saveOrUpdate(Reply reply, String makerName) {

        User maker = userRepository.findByUsername(makerName).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

        reply.setUser(maker);
        return replyRepository.save(reply);
    }

}
