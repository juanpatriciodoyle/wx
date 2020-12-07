package com.iv.wx.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iv.wx.model.Comment;
import com.iv.wx.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> getAll() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/comments/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
    }

    @Override
    public Comment getByCommentId(Integer id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/comments/"+id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), Comment.class);
    }

    @Override
    public List<Comment> getByPostId(Integer id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/comments?postId="+id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
    }
}
