package com.twitter.clone.twitterclone.controller;

import com.twitter.clone.twitterclone.entity.User;
import com.twitter.clone.twitterclone.exception.MapValidationErrorService;
import com.twitter.clone.twitterclone.payload.JWTLoginSuccessResponse;
import com.twitter.clone.twitterclone.payload.LoginRequest;
import com.twitter.clone.twitterclone.response.RESTApiResponse;
import com.twitter.clone.twitterclone.security.JwtTokenProvider;
import com.twitter.clone.twitterclone.security.SecurityConstant;
import com.twitter.clone.twitterclone.service.UserService;
import com.twitter.clone.twitterclone.util.Status;
import com.twitter.clone.twitterclone.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt  = SecurityConstant.TOKEN_PREFIX+tokenProvider.genereteToken(authentication);

        return RESTApiResponse.responseSuccess(
                jwt,
                Status.SUCCESS,
                ""
        );

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {

        userValidator.validate(user, result);

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        User newUser = userService.saveUser(user);

        return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
    }


}
