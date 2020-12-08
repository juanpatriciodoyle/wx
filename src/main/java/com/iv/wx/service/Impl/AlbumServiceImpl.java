package com.iv.wx.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iv.wx.model.Album;
import com.iv.wx.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class AlbumServiceImpl implements AlbumService {

    String url = "https://jsonplaceholder.typicode.com/albums/";

    @Override
    public List<Album> getAll() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
    }

    @Override
    public Album getById(Integer id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        url += id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), Album.class);
    }

    @Override
    public List<Album> getByIdUser(Integer id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        url += "?userId="+id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(response.getBody(), new TypeReference<>() {});
    }

    @Override
    public Optional<Album> save(Album album) {
        return Optional.empty();
    }
}
