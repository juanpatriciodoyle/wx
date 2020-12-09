package com.iv.wx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.*;
import com.iv.wx.model.user.User;
import com.iv.wx.service.*;
import com.iv.wx.to.SaveAlbumRequestTo;
import com.iv.wx.to.SaveAlbumResponseTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Log
@RestController
@RequiredArgsConstructor
public class WxController implements WxControllerApi {

    private final UserService userService;
    private final PostService postService;
    private final AlbumService albumService;
    private final PhotoService photoService;
    private final CommentService commentService;
    private final PermissionService permissionService;

    //  User
    @Override
    public ResponseEntity<User> getUser(Integer id) {

        try {
            return ResponseEntity.ok(userService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<User> saveUser(User user) {
        Optional<User> userSaved = userService.save(user);
        if (userSaved.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userSaved.get(), HttpStatus.ACCEPTED);
    }

    //  Post
    @Override
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            return ResponseEntity.ok(postService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<Post> getPost(Integer id) {
        try {
            return ResponseEntity.ok(postService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    //  Album
    @Override
    public ResponseEntity<List<Album>> getAllAlbums() {
        try {
            return ResponseEntity.ok(albumService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<Album> getAlbum(Integer id) {
        try {
            return ResponseEntity.ok(albumService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<List<Album>> getAlbumsByIdUser(Integer id) {
        try {
            return ResponseEntity.ok(albumService.getByIdUser(id));

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<SaveAlbumResponseTo> register(SaveAlbumRequestTo request) {

        User user;
        Optional<Album> album;
        Permission permission;

        album = albumService.save(request.getAlbum());
        if (album.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (request.getUser().getId() == null) {
            Optional<User> newUser = userService.save(request.getUser());
            if (newUser.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            user = newUser.get();
        }else {
            try {
               user = userService.getById(request.getUser().getId());

            } catch (JsonProcessingException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        Optional<Permission> newPermission;
        try {
            Optional<Permission> savedPermission = permissionService.getByIdUser(user.getId());

            if (savedPermission.isPresent()) {
                permission = savedPermission.get();
                List<Integer> read = new ArrayList<>(Arrays.asList(permission.getRead().clone()));
                List<Integer> write = new ArrayList<>(Arrays.asList(permission.getWrite().clone()));
                if (request.getRead()) read.add(album.get().getId());
                if (request.getWrite()) write.add(album.get().getId());
                permission.setRead(read.toArray(new Integer[0]));
                permission.setWrite(write.toArray(new Integer[0]));

            } else {
                permission = Permission.builder()
                        .userId(user.getId())
                        .build();
                if (request.getRead()) permission.setRead(new Integer[]{album.get().getId()});
                if (request.getRead()) permission.setWrite(new Integer[]{album.get().getId()});

            }
            newPermission = permissionService.save(permission);
            if (newPermission.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        SaveAlbumResponseTo response = SaveAlbumResponseTo.builder()
                .album(album.get())
                .user(user)
                .permission(newPermission.get())
                .build();

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    //  Photo
    @Override
    public ResponseEntity<List<Photo>> getAllPhotos() {
        try {
            return ResponseEntity.ok(photoService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<Photo> getPhoto(Integer id) {
        try {
            return ResponseEntity.ok(photoService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<List<Photo>> photosByUser(Integer id) {
        try {
            List<Album> albums = albumService.getByIdUser(id);
            List<Photo> photos = new ArrayList<>();

            if (albums.size() > 0) {
                List<Integer> albumsId = albums.stream().map(Album::getId).collect(Collectors.toList());
                albumsId = albumsId.stream().distinct().collect(Collectors.toList());
                albumsId.forEach(idAlbum -> {

                    try {
                        photos.addAll(photoService.getByAlbumId(idAlbum));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                });
                return ResponseEntity.ok(photos);
            }

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //  Comment
    @Override
    public ResponseEntity<List<Comment>> getAllComments() {
        try {
            return ResponseEntity.ok(commentService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<Comment> getComment(Integer id) {
        try {
            return ResponseEntity.ok(commentService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public ResponseEntity<List<Comment>> commentsByUser(Integer id) {
        try {
            List<Post> posts = postService.getByUserId(id);
            List<Comment> comments = new ArrayList<>();

            if (posts.size() > 0) {
                List<Integer> postsId = posts.stream().map(Post::getId).collect(Collectors.toList());
                postsId = postsId.stream().distinct().collect(Collectors.toList());
                postsId.forEach(idPost -> {

                    try {
                        comments.addAll(commentService.getByPostId(idPost));
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                });
                return ResponseEntity.ok(comments);
            }

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //  Permission
    @Override
    public ResponseEntity<Permission> getPermissionByIdUser(Integer id) {
        try {
            Optional<Permission> permission = permissionService.getByIdUser(id);
            if (permission.isPresent()) ResponseEntity.ok(permission);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Permission> savePermission(Permission permission) {
        Optional<Permission> permissionSaved = permissionService.save(permission);
        return permissionSaved.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
