package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Photo;

import java.util.List;

public interface PhotoService {
    List<Photo> getAll() throws JsonProcessingException;
    Photo getById(Integer id) throws JsonProcessingException;
    List<Photo> getByAlbumId(Integer id) throws JsonProcessingException;
}
