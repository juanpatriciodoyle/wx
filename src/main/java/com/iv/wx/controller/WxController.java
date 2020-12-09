package com.iv.wx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.*;
import com.iv.wx.model.user.User;
import com.iv.wx.service.*;
import com.iv.wx.to.PermissionsRequestTo;
import com.iv.wx.to.SaveAlbumRequestTo;
import com.iv.wx.to.SaveAlbumResponseTo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Console;
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

    /** Returns User by the user_id given **/
    @Override
    public ResponseEntity<User> getUser(Integer id) {

        try {
            return ResponseEntity.ok(userService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    /** Returns all Users found in the Placeholder **/
    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /** Returns all Users regarding a specific album with a certain kind of permissions **/
    @Override
    public ResponseEntity<List<User>> getAllUsersByPermissions(Integer albumId, Boolean write, Boolean read) {
        PermissionsRequestTo permissionsRequestTo = PermissionsRequestTo.builder()
                .albumId(albumId)
                .write(write)
                .read(read)
                .build();
        try {
            List<User> users = new ArrayList<>();
            Optional<List<Integer>> usersIds = permissionService.getUsersByPermissionAndAlbum(permissionsRequestTo);
            usersIds.ifPresent(idList -> idList.forEach(id -> {
                try {
                    users.add(userService.getById(id));
                } catch (Exception e) {
                    log.warning("Error while getting user with id: "+id);
                }
            }));
            return ResponseEntity.ok(users);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /** Creates and returns the new User requested **/
    @Override
    public ResponseEntity<User> saveUser(User user) {
        Optional<User> userSaved = userService.save(user);
        if (userSaved.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(userSaved.get(), HttpStatus.ACCEPTED);
    }

    /** Returns all Posts found in the Placeholder **/
    @Override
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            return ResponseEntity.ok(postService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /** Returns Post by the post_id given **/
    @Override
    public ResponseEntity<Post> getPost(Integer id) {
        try {
            return ResponseEntity.ok(postService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /** Returns all Albums found in the Placeholder **/
    @Override
    public ResponseEntity<List<Album>> getAllAlbums() {
        try {
            return ResponseEntity.ok(albumService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /** Returns Album by the album_id given **/
    @Override
    public ResponseEntity<Album> getAlbum(Integer id) {
        try {
            return ResponseEntity.ok(albumService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /** Returns all Albums that corresponds to the user_id given **/
    @Override
    public ResponseEntity<List<Album>> getAlbumsByIdUser(Integer id) {
        try {
            return ResponseEntity.ok(albumService.getByIdUser(id));

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /**
     * Register an album shared with a user and their permissions
     * User can be new
     * Doesn't matter if user have already some Permissions stored
     * Album must be new
     * **/
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
        } else {
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

    /** Returns all Photos found in the Placeholder **/
    @Override
    public ResponseEntity<List<Photo>> getAllPhotos() {
        try {
            return ResponseEntity.ok(photoService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /** Returns Photo by the photo_id given **/
    @Override
    public ResponseEntity<Photo> getPhoto(Integer id) {
        try {
            return ResponseEntity.ok(photoService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /**
     * Returns a List of Photos that corresponds to the user_id given
     *  If there are none, a 404(Not Found) error will be returned
     *  **/
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
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** Returns all Comments found in the Placeholder **/
    @Override
    public ResponseEntity<List<Comment>> getAllComments() {
        try {
            return ResponseEntity.ok(commentService.getAll());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /** Returns Comment by the comment_id given **/
    @Override
    public ResponseEntity<Comment> getComment(Integer id) {
        try {
            return ResponseEntity.ok(commentService.getById(id));

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /**
     * Returns a List of Comments that corresponds to the user_id given
     *  If there are none, a 404(Not Found) error will be returned
     *  **/
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
                        log.warning("Error while getting Comment with user id: "+id);
                    }

                });
                return ResponseEntity.ok(comments);
            }

        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /** Returns all Permissions that corresponds to the given user_id **/
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

    /** Returns the updated Permissions to an specific album for the requested user **/
    @Override
    public ResponseEntity<Permission> changePermissions(Integer id, Integer album_id, Boolean write , Boolean read) {

        PermissionsRequestTo permissionsRequestTo = PermissionsRequestTo.builder()
                .albumId(album_id)
                .write(write)
                .read(read)
                .build();

        try {
            Optional<Permission> optionalPermission = permissionService.getByIdUser(id);
            if (optionalPermission.isPresent()) {
                Permission permission = optionalPermission.get();
                Integer albumId = permissionsRequestTo.getAlbumId();
                List<Integer> reads = new ArrayList<>(Arrays.asList(permission.getRead().clone()));
                List<Integer> writes = new ArrayList<>(Arrays.asList(permission.getWrite().clone()));

                if (permissionsRequestTo.getRead()) {
                    if (!reads.contains(albumId)) reads.add(albumId);
                } else {
                    reads.remove(albumId);
                }
                permission.setRead(reads.toArray(new Integer[0]));

                if (permissionsRequestTo.getWrite()) {
                    if (!writes.contains(albumId)) writes.add(albumId);
                } else {
                    writes.remove(albumId);
                }
                permission.setWrite(writes.toArray(new Integer[0]));

                Optional<Permission> response = permissionService.save(permission);
                return response.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    /** Creates and returns the new Permission requested **/
    @Override
    public ResponseEntity<Permission> savePermission(Permission permission) {
        Optional<Permission> permissionSaved = permissionService.save(permission);
        return permissionSaved.map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
