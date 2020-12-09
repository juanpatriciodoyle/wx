package com.iv.wx.to;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionsRequestTo {
    private Integer albumId;
    private Boolean write;
    private Boolean read;

}
