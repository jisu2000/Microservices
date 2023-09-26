package com.jisu.user.service;

import java.util.ArrayList;
import java.util.List;

import com.jisu.user.Model.User;

public interface UserService {

	User saveUser(User u);

	String getName(String id);

	List<User> getAll();

	User getUser(String id);

	User delUser(String id);

	User updateUser(User u, String id);

	ArrayList<User> findbyname(String name);
}
