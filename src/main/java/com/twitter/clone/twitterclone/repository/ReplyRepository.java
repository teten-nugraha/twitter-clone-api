package com.twitter.clone.twitterclone.repository;

import com.twitter.clone.twitterclone.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
