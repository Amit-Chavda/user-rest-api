package com.restapi.conroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.entity.User;
import com.restapi.service.UserService;
import com.restapi.serviceImpl.UserServiceImpl;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("User/")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{id}")
	public ResponseEntity<User> findUserById(@PathVariable Long id) {
		User user = userService.findById(id);
		if (user == null) {
			user = new User();
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> removeUserById(@PathVariable Long id) {
		User user = userService.findById(id);
		if (user == null) {
			user = new User();
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PostMapping("/SaveAll")
	public ResponseEntity<String> saveAll(@RequestBody List<User> users) {
		userService.saveAll(users);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(path = "/Save", method = RequestMethod.POST)
	public ResponseEntity<User> save(@RequestBody User user) {

		User output = userService.save(user);
		System.out.println(output.toString());
		return new ResponseEntity<User>(output, HttpStatus.OK);
	}

	@GetMapping("/All")
	public ResponseEntity<List<User>> findAll() {
		List<User> users = userService.findAll();
		if (users == null) {
			users = new ArrayList<>();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/Search")
	public ResponseEntity<List<User>> search(@RequestParam String q) {

		System.out.println(q);
		List<User> users = userService.search(q);

		if (users == null) {
			users = new ArrayList<>();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		}
		return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
	}

}
