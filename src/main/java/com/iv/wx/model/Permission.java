package com.iv.wx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Permission {
    private Integer userId;
    private Integer[] read;
    private Integer[] write;
}
