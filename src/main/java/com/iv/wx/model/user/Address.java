package com.iv.wx.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;

    private Geo geo;
}
