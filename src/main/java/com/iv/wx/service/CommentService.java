package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAll() throws JsonProcessingException;
    Comment getById(Integer id) throws JsonProcessingException;
    List<Comment> getByPostId(Integer id) throws JsonProcessingException;
}
