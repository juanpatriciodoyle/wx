package com.iv.wx.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iv.wx.model.Album;
import com.iv.wx.model.Post;
import com.iv.wx.model.user.User;
import com.iv.wx.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {
    String url = "https://jsonplaceholder.typicode.com/users/";

    @Override
    public List<User> getAll() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
    }

    @Override
    public User getById(Integer id) throws JsonProcessingException, HttpClientErrorException {
        RestTemplate restTemplate = new RestTemplate();
        url+=id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), User.class);
    }

    @Override
    public Optional<User> save(User user) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> request = restTemplate.postForEntity(url, user, User.class);

        return Optional.ofNullable(request.getBody());
    }

}
