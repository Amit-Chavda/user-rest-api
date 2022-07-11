package com.restapi.conroller;

import com.restapi.dto.UserDto;
import com.restapi.entity.User;
import com.restapi.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("User/")
public class UserController {

	private UserService userService;


	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public UserDto findUserById(@PathVariable Long id) {
		return userService.findById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> removeUserById(@PathVariable Long id) {
		//User user = userService.findById(id);
		return new ResponseEntity<User>(new User(), HttpStatus.OK);
	}

	@PostMapping("/SaveAll")
	public ResponseEntity<String> saveAll(@RequestBody List<User> users) {
		userService.saveAll(users);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(path = "/Save", method = RequestMethod.POST)
	public ResponseEntity<User> save(@RequestBody User user) {
		User output = userService.save(user);
		return new ResponseEntity<User>(output, HttpStatus.OK);
	}

	@GetMapping("/All")
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

}
