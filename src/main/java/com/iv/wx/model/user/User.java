package com.iv.wx.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {

    private Integer id;
    private String name;
    private String username;
    private String email;
    private String phone;
    private String website;

    private Address address;
    private Company company;
}
