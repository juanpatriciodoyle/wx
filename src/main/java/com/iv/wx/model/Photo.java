package com.iv.wx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Photo {
    private Integer id;
    private Integer albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
}
