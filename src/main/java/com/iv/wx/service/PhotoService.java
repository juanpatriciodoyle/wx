package com.iv.wx.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Photo;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

public interface PhotoService {
    List<Photo> getAll() throws JsonProcessingException, HttpClientErrorException;
    Photo getById(Integer id) throws JsonProcessingException, HttpClientErrorException;
    List<Photo> getByAlbumId(Integer id) throws JsonProcessingException;
}
