package com.iv.wx.to;

import com.iv.wx.model.Album;
import com.iv.wx.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveAlbumRequestTo {
    private Album album;
    private User user;
    private Boolean write;
    private Boolean read;

}
