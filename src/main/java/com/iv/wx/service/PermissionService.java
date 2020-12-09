package com.iv.wx.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Permission;
import com.iv.wx.to.PermissionsRequestTo;

import java.util.List;
import java.util.Optional;

public interface PermissionService {

    Optional<Permission> save(Permission permission);
    Optional<Permission> getByIdUser(Integer id) throws JsonProcessingException;
    Optional<List<Integer>> getUsersByPermissionAndAlbum(PermissionsRequestTo permissionsRequestTo) throws JsonProcessingException;

}
