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
	public String removeUserById(@PathVariable Long id) {
		return userService.removeById(id);
	}

	@PostMapping("/SaveAll")
	public String saveAll(@RequestBody List<UserDto> userDtos) {
		userService.saveAll(userDtos);
		return "success";
	}

	@RequestMapping(path = "/Save", method = RequestMethod.POST)
	public UserDto save(@RequestBody UserDto user) {
		return  userService.save(user);
	}

	@GetMapping("/All")
	public List<UserDto> findAll() {
		return userService.findAll();
	}

}
