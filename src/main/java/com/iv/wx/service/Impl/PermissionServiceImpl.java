package com.iv.wx.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iv.wx.model.Permission;
import com.iv.wx.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Component
public class PermissionServiceImpl implements PermissionService {

    String url = "https://my-json-server.typicode.com/juanpatriciodoyle/my-json-server/permissions/";

    @Override
    public Optional<Permission> save(Permission permission) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Permission> request = restTemplate.postForEntity(url, permission, Permission.class);

        return Optional.ofNullable(request.getBody());
    }

    @Override
    public Permission getByIdUser(Integer id) throws JsonProcessingException, HttpClientErrorException {
        RestTemplate restTemplate = new RestTemplate();
        url = url.substring(0,url.length()-1)+"?userId="+id;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        ObjectMapper objectMapper = new ObjectMapper();

        List<Permission> permissions = objectMapper.readValue(response.getBody(), new TypeReference<>() {});
        return permissions.get(0);
    }

    @Override
    public Boolean delete(Integer id) {
        RestTemplate restTemplate = new RestTemplate();
        url = url.substring(0,url.length()-1)+"?userId="+id;
        try{
            restTemplate.delete(url);
            return true;

        }catch (RestClientException e){
            return false;

        }
    }
}
