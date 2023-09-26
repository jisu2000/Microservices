package com.jisu.hotel.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    private String userId;
    private String name;
    private String email;
    private String about;
    
    private List<Rating> ratings;

}
