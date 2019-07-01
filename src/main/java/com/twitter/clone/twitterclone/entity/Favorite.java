package com.twitter.clone.twitterclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private Tweet tweet;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private User user;

    @JsonIgnore
    private Date create_At;

    @JsonIgnore
    private Date update_At;

    public Favorite(Tweet tweet, User user) {
        this.tweet = tweet;
        this.user = user;
    }

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.update_At = new Date();
    }

}
