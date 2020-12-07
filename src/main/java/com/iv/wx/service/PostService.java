package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Post;

import java.util.List;

public interface PostService {

    List<Post> getAll() throws JsonProcessingException;
    Post getByPostId(Integer id) throws JsonProcessingException;
    List<Post> getByUserId(Integer id) throws JsonProcessingException;
}
