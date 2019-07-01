package com.twitter.clone.twitterclone.exception;

import lombok.Data;

@Data
public class ResponseException {

    private String exception;

    public ResponseException(String exception) {
        this.exception = exception;
    }
}
