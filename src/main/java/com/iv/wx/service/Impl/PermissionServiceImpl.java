package com.iv.wx.service.Impl;

import com.iv.wx.service.PermissionService;
import org.springframework.stereotype.Component;
import java.security.Permission;
import java.util.Optional;

@Component
public class PermissionServiceImpl implements PermissionService {
    String url = "https://jsonplaceholder.typicode.com/users/";

    @Override
    public Optional<Permission> save(Permission permission) {
        return Optional.empty();
    }
}
