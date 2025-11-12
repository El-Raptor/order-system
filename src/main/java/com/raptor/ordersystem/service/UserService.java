package com.raptor.ordersystem.service;

import com.raptor.ordersystem.dto.CreateUserDTO;
import com.raptor.ordersystem.dto.UserDTO;
import com.raptor.ordersystem.dto.UserSummaryDTO;
import com.raptor.ordersystem.entity.User;
import com.raptor.ordersystem.mapper.UserMapper;
import com.raptor.ordersystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User findUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    public User register(CreateUserDTO userDTO) {
        return userRepo.save(UserMapper.toEntity(userDTO));
    }

    public User alterUser(UserDTO dto) {
        return userRepo.save(UserMapper.toEntity(dto));
    }

    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }
}
