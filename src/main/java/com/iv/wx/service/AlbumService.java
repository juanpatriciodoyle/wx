package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Album;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> getAll() throws JsonProcessingException, HttpClientErrorException;
    Album getById(Integer id) throws JsonProcessingException;
    List<Album> getByIdUser(Integer id) throws JsonProcessingException, HttpClientErrorException;
    Optional<Album> save(Album album);
}
