package com.restapi;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.restapi.dto.UserDto;
import com.restapi.entity.User;

@SpringBootApplication
public class UserRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserRestApiApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addMappings(new PropertyMap<User, UserDto>() {
			protected void configure() {
				map().setEmailAddress(source.getEmail());
				map().setPhysicalAddress(source.getAddress());
			}
		});
		modelMapper.addMappings(new PropertyMap<UserDto, User>() {
			protected void configure() {
				map().setEmail(source.getEmailAddress());
				map().setAddress(source.getPhysicalAddress());
			}
		});
		return modelMapper;
	}
}
