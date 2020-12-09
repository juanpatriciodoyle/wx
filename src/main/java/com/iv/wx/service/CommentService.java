package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Comment;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface CommentService {
    List<Comment> getAll() throws JsonProcessingException, HttpClientErrorException;
    Comment getById(Integer id) throws JsonProcessingException, HttpClientErrorException;
    List<Comment> getByPostId(Integer id) throws JsonProcessingException;
}
