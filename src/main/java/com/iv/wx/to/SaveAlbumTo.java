package com.iv.wx.to;

import com.iv.wx.model.Album;
import com.iv.wx.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveAlbumTo {
    private Album album;
    private User user;
    private Boolean write;
    private Boolean read;

}
