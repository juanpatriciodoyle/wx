package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Album;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> getAll() throws JsonProcessingException;
    Album getById(Integer id) throws JsonProcessingException;
    List<Album> getByIdUser(Integer id) throws JsonProcessingException;
    Optional<Album> save(Album album);
}
