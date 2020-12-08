package com.iv.wx.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iv.wx.model.Photo;
import com.iv.wx.model.Post;
import com.iv.wx.service.PhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class PhotoServiceImpl implements PhotoService {

    String url = "https://jsonplaceholder.typicode.com/photos/";

    @Override
    public List<Photo> getAll() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://jsonplaceholder.typicode.com/photos/";
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
    }

    @Override
    public Photo getById(Integer id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        url += id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), Photo.class);
    }

    @Override
    public List<Photo> getByAlbumId(Integer id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        url += "?albumId="+id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
    }
}
