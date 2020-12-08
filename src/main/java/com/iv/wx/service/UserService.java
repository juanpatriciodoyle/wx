package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAll() throws JsonProcessingException;
    User getById(Integer id) throws JsonProcessingException;
    Optional<User> save(User user);

}
