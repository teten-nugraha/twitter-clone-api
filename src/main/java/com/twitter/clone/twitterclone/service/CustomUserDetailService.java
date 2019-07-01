package com.twitter.clone.twitterclone.service;

import com.twitter.clone.twitterclone.entity.User;
import com.twitter.clone.twitterclone.exception.ResourceNotFoundException;
import com.twitter.clone.twitterclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws ResourceNotFoundException {

        return userRepository.findByUsername(s).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

    }

    @Transactional
    public User loadUsersById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));

    }
}
