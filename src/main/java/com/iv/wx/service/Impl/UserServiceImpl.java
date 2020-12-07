package com.iv.wx.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iv.wx.model.Post;
import com.iv.wx.model.user.User;
import com.iv.wx.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getAll() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/users/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
    }

    @Override
    public User getByUserId(Integer id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/users/"+id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), User.class);
    }

}
