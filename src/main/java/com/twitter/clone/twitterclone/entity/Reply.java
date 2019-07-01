package com.twitter.clone.twitterclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Data
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private Tweet tweet;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    private User user;

    @NotBlank(message = "Reply is required")
    private String reply;

    @JsonIgnore
    private Date create_At;

    @JsonIgnore
    private Date update_At;

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.update_At = new Date();
    }

}
