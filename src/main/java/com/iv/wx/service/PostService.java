package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Post;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface PostService {

    List<Post> getAll() throws JsonProcessingException, HttpClientErrorException;
    Post getById(Integer id) throws JsonProcessingException, HttpClientErrorException;
    List<Post> getByUserId(Integer id) throws JsonProcessingException;
}
