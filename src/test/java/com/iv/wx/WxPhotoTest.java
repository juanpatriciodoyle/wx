package com.iv.wx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WxPhotoTest {

    private final String url = "http://localhost:8080/wx";


    @Test
    void getPhotos() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url+"/photos", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
    }

    @Test
    void getPhotosById() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url+"/photos?id=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
    }

    @Test
    void photosByUser() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url+"/photosByUser?id=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
    }
    
}
