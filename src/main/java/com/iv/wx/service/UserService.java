package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.user.User;

import java.util.List;

public interface UserService {

    List<User> getAll() throws JsonProcessingException;

    User getByUserId(Integer id) throws JsonProcessingException;
}
