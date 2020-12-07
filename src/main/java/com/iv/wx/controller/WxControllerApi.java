package com.iv.wx.controller;

import com.iv.wx.model.Album;
import com.iv.wx.model.Comment;
import com.iv.wx.model.Photo;
import com.iv.wx.model.Post;
import com.iv.wx.model.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(path = "/wx")
public interface WxControllerApi {

    //  USERS
    @RequestMapping(value = "/user",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<User> getUser(@RequestParam(name = "id") Integer id);

    @RequestMapping(value = "/users",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<User>> getAllUsers();

    
    //  POSTS
    @RequestMapping(value = "/posts",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Post>> getAllPosts();

    @RequestMapping(value = "/post",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Post> getPost(@RequestParam(name = "id") Integer id);

    
    //  Album
    @RequestMapping(value = "/albums",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Album>> getAllAlbums();

    @RequestMapping(value = "/album",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Album> getAlbum(@RequestParam(name = "id") Integer id);

    @RequestMapping(value = "/albumsByUser",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Album>> getAlbumsByIdUser(@RequestParam(name = "id") Integer id);


    //  Photos
    @RequestMapping(value = "/photos",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Photo>> getAllPhotos();

    @RequestMapping(value = "/photo",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Photo> getPhoto(@RequestParam(name = "id") Integer id);

    @RequestMapping(value = "/photosByUser",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Photo>> photosByUser(@RequestParam(name = "id") Integer id);

    //  Comments
    @RequestMapping(value = "/comments",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Comment>> getAllComments();

    @RequestMapping(value = "/comment",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Comment> getComment(@RequestParam(name = "id") Integer id);

    @RequestMapping(value = "/commentsByUser",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Comment>> commentsByUser(@RequestParam(name = "id") Integer id);


}
