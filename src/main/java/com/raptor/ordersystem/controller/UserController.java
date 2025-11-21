package com.raptor.ordersystem.controller;

import com.raptor.ordersystem.dto.CreateUserDTO;
import com.raptor.ordersystem.dto.UserDTO;
import com.raptor.ordersystem.dto.UserLoginDTO;
import com.raptor.ordersystem.dto.UserSummaryDTO;
import com.raptor.ordersystem.entity.User;
import com.raptor.ordersystem.mapper.UserMapper;
import com.raptor.ordersystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable int id) {
        var user = userService.findUserById(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(UserMapper.toDto(user), HttpStatus.OK);
    }

    @GetMapping("/users/email")
    public ResponseEntity<UserDTO> findByEmail(@RequestParam String email) {
        var user = userService.findUserByEmail(email);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(UserMapper.toDto(user), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserSummaryDTO> createUser(@RequestBody CreateUserDTO dto) {
        var user = userService.register(UserMapper.toEntity(dto));
        URI uri = URI.create(String.format("/users/%s", user.getId()));
        return ResponseEntity.created(uri).body(UserMapper.toSummaryDTO(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
        var response = userService.verify(UserMapper.toEntity(dto));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        User user = userService.findUserById(id);

        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userDTO.setId(id);
        user = UserMapper.toEntity(userDTO);
        var updatedUser = UserMapper.toDto(userService.alterUser(user));
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        var user = userService.findUserById(id);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
