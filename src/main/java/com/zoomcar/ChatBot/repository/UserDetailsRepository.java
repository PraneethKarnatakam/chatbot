package com.zoomcar.ChatBot.repository;

import com.zoomcar.ChatBot.entity.UserCustomDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserCustomDetails,Long> {
    UserCustomDetails findByUsername(String username);
}
