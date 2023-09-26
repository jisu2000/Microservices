package com.jisu.user.dao;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jisu.user.Model.User;

public interface UserDao extends JpaRepository<User, String> {
    public ArrayList<User> findByName(String name);
}
