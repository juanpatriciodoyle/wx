package com.iv.wx.service;


import java.security.Permission;
import java.util.Optional;

public interface PermissionService {

    Optional<Permission> save(Permission permission);

}
