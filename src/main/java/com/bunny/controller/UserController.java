package com.bunny.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bunny.model.Response;
import com.bunny.model.User;
import com.bunny.model.dto.UserRegistrationDTO;
import com.bunny.service.userService;
import com.bunny.utils.exception.BadRequestError;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/user")
@Api(value = "Learning JPA")
public class UserController {

	@Autowired
	private userService userService;

	@ApiOperation(value = "Register new User in database and return response")
	@PostMapping(value = "/register", produces = "application/json")
	public ResponseEntity<Response> registerUser(
			@ApiParam(value = "Student Registration object store in database table", required = true) @RequestBody UserRegistrationDTO request) {
		try {
			Optional<User> maybeUser = userService.registerUser(request);
			if (!maybeUser.isPresent()) {
				return ResponseEntity.badRequest()
						.body(new Response("User Registration Failed", HttpStatus.BAD_REQUEST.value()));
			}
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(new Response("User Registration Successfully done", HttpStatus.CREATED.value()));
		} catch (BadRequestError e) {
			return ResponseEntity.badRequest().body(new Response(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}

	@ApiOperation(value = "Get list of all register students on basics of their Location, Status, Registration Date, Registration ID and Name", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully Register"),
			@ApiResponse(code = 400, message = "Bad Credentials in Admin Login"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })

	@GetMapping("/users")
	public List<User> getUsers(@RequestParam(required = false, value = "firstName") String firstName,
			@RequestParam(required = false, value = "lastName") String lastName,
			@RequestParam(required = false, value = "email") String email,
			@RequestParam(required = false, value = "registerDate") String registerDate,
			@RequestParam(required = false, value = "mobileNumber") String mobileNumber) {
		return userService.getUsers(firstName, lastName, email, registerDate, mobileNumber);
	}

	@ApiOperation(value = "Update User Information")
	@PutMapping("/update")
	public ResponseEntity<Response> updateUser(@RequestBody List<User> users) {
		String msg = userService.updateUser(users);
		return ResponseEntity.status(HttpStatus.OK).body(new Response(msg, HttpStatus.OK.value()));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> deleteSource(@PathVariable(name = "id") String userId) {
		if (!userService.deleteSource(userId)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new Response("User Not Found", HttpStatus.NOT_FOUND.value()));
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new Response("User Deleted Successfully", HttpStatus.OK.value()));

	}
}
