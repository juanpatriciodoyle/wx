package com.iv.wx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Post {
    private Integer id;
    private Integer userId;
    private String title;
    private String body;
}
