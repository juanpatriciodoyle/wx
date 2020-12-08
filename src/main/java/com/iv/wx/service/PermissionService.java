package com.iv.wx.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.iv.wx.model.Permission;

import java.util.Optional;

public interface PermissionService {

    Optional<Permission> save(Permission permission);
    Permission getByIdUser(Integer id) throws JsonProcessingException;
    Boolean delete(Integer id);

}
