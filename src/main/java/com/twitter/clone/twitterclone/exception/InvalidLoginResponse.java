package com.twitter.clone.twitterclone.exception;

import lombok.Data;

@Data
public class InvalidLoginResponse {

    private String error;

    public InvalidLoginResponse() {

        this.error = "Authentication Error";

    }
}
