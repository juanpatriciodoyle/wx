package com.iv.wx.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iv.wx.model.Permission;
import com.iv.wx.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Component
public class PermissionServiceImpl implements PermissionService {

    String url = "https://my-json-server.typicode.com/juanpatriciodoyle/my-json-server/permissions/";

    @Override
    public Optional<Permission> save(Permission permission) {
        RestTemplate restTemplate = new RestTemplate();
        if (permission.getId() == null){
            return Optional.ofNullable(restTemplate.postForEntity(url, permission, Permission.class).getBody());

        }else {
            String url_put = url + permission.getId();
            restTemplate.put(url_put, permission);
            return Optional.of(permission);

        }
    }

    @Override
    public Optional<Permission> getByIdUser(Integer id) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String url_user = url.substring(0,url.length()-1)+"?userId="+id;
        ResponseEntity<String> response = restTemplate.getForEntity(url_user, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            List<Permission> permissions = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
            if (permissions.isEmpty()) return Optional.empty();
            return Optional.of(permissions.get(0));

        }catch (HttpClientErrorException e){
            return Optional.empty();
        }
    }

}
