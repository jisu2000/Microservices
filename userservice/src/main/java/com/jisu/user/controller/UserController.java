package com.jisu.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jisu.user.Model.User;
import com.jisu.user.service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User user1 = this.service.saveUser(user);

		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}

	@GetMapping("/{userId}")
	@CircuitBreaker(name = "rating_hotel", fallbackMethod = "rating_hotel_fallback")
	public ResponseEntity<User> getById(@PathVariable String userId) {
		User u = service.getUser(userId);

		return ResponseEntity.ok(u);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		List<User> list = this.service.getAll();
		return ResponseEntity.ok(list);
	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable String userId) {
		User u = service.delUser(userId);
		return ResponseEntity.ok(u);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@PathVariable String userId, @RequestBody User user) {
		User existingUser = this.service.getUser(userId);

		if (existingUser != null) {
			// Update the existing user's properties
			existingUser.setName(user.getName());
			existingUser.setEmail(user.getEmail());
			existingUser.setAbout(user.getAbout());

			this.service.updateUser(existingUser, userId);

			return ResponseEntity.ok(existingUser);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("name/{userName}")
	public ResponseEntity<List<User>> getByname(@PathVariable String userName) {

		List<User> list = this.service.findbyname(userName);

		return ResponseEntity.ok(list);
	}

	@GetMapping("fullname/{id}")
	public ResponseEntity<String> getName(@PathVariable String id) {
		String name = this.service.getName(id);
		return ResponseEntity.ok(name);
	}

	public ResponseEntity<User> rating_hotel_fallback(String userId,Exception ex){
		User user=new User();
		user.setUserId("-99");
		user.setName("dummy");
		user.setEmail("@com");
		user.setAbout("Created dummy user because of service failure");
		return new ResponseEntity<User>(user,HttpStatus.FAILED_DEPENDENCY);
	}

}
