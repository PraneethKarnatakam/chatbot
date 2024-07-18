package com.zoomcar.ChatBot.service;

import com.zoomcar.ChatBot.entity.UserCustomDetails;
import com.zoomcar.ChatBot.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsCustomService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserCustomDetails findByUsername(String username) {
        return userDetailsRepository.findByUsername(username);
    }

    public UserCustomDetails save(UserCustomDetails userDetails) {
        return userDetailsRepository.save(userDetails);
    }


}
