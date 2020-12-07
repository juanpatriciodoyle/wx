package com.iv.wx;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WxCommentTest {

    private final String url = "http://localhost:8080/wx";


    @Test
    void getAlbums() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url+"/comments", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
    }

    @Test
    void getAlbumsById() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url+"/comments?id=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
    }

    @Test
    void commentByUser() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url+"/commentsByUser?id=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.valueOf(200));
    }
    
}
