package com.twitter.clone.twitterclone.exception;

import com.twitter.clone.twitterclone.response.RESTApiResponse;
import com.twitter.clone.twitterclone.util.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler
    public final ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ResponseException responseException = new ResponseException(ex.getMessage());

        return RESTApiResponse.responseError(
          responseException,
                Status.ERROR,
                null,
                null
        );

    }

    @ExceptionHandler
    public final ResponseEntity<?> resourceAlreadyException(ResourceAlreadyExistException ex, WebRequest request) {
        ResponseException responseException = new ResponseException(ex.getMessage());

        return RESTApiResponse.responseError(
                responseException,
                Status.ERROR,
                null,
                null
        );
    }

}
