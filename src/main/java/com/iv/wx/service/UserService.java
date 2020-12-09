package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.user.User;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll() throws JsonProcessingException, HttpClientErrorException;
    User getById(Integer id) throws JsonProcessingException, HttpClientErrorException;
    Optional<User> save(User user);

}
