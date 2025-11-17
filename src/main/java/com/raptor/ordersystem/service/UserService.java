package com.raptor.ordersystem.service;

import com.raptor.ordersystem.dto.CreateUserDTO;
import com.raptor.ordersystem.dto.UserDTO;
import com.raptor.ordersystem.entity.User;
import com.raptor.ordersystem.mapper.UserMapper;
import com.raptor.ordersystem.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * Find a user by its ID.
     *
     * @param id User id
     * @return <code>User</code> User based on the given Id.
     */
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public User findUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    //@PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    public User register(CreateUserDTO dto) {
        // Encode password before saving it
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        dto.setPassword(encoder.encode(dto.getPassword()));
        return userRepo.save(UserMapper.toEntity(dto));
    }

    public User alterUser(UserDTO dto) {
        // Encode password before saving it
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        dto.setPassword(encoder.encode(dto.getPassword()));
        return userRepo.save(UserMapper.toEntity(dto));
    }

    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }
}
