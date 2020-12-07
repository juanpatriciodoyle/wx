package com.iv.wx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Comment {
    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;

}
