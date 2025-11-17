package com.raptor.ordersystem.service;

import com.raptor.ordersystem.dto.CreateUserDTO;
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
     * Retrieves a user based on the provided ID.
     *
     * <p>Access is restricted by a security rule: the authenticated user
     * may retrieve their own information, while users with the ADMIN role
     * may retrieve any user's data.</p>
     *
     * @param id the ID used to look up the user.
     * @return the {@code User} associated with the given ID.
     */
    @PreAuthorize("#id == authentication.principal.id or hasRole('ADMIN')")
    public User findUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    /**
     * Retrieves a user based on the provided e-mail address.
     *
     * <p>Access is restricted by a security rule: the authenticated user may
     * retrieve their own information, while users with the ADMIN role may
     * retrieve any user's data.</p>
     *
     * @param email the e-mail address used to look up the user.
     * @return the {@code User} associated with the given e-mail, or {@code null}
     * if no user is found.
     */
    @PreAuthorize("#email == authentication.principal.email or hasRole('ADMIN')")
    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }

    /**
     * Registers a new User in the system.
     *
     * <p>The method encodes the provided plaintext password before persisting the user data
     * to the database.</p>
     *
     * @param user the object containing the user information to create.
     * @return the newly created user entity.
     */
    public User register(User user) {
        encodePassword(user);
        return userRepo.save(user);
    }

    /**
     * Alters an existing User int the system.
     *
     * <p>Access is restricted by a security rule: only authenticated user may
     * alter their own information.</p>
     * <p>The method encodes the provided plaintext password before persisting the user data
     * to the database.</p>
     *
     * @param user the object containing the user information to create.
     * @return the {@code User} associated with the given ID.
     */
    @PreAuthorize("#user.getId() == authentication.principal.id")
    public User alterUser(User user) {

        User existingUser = userRepo.findById(user.getId()).orElse(null);

        if (existingUser == null)
            throw new RuntimeException("User not found.");

        if (user.getName() != null) {
            existingUser.setName(user.getName());
        }

        if (user.getEmail() != null) {
            existingUser.setEmail(user.getEmail());
        }

        if (user.getRole() != null)
            existingUser.setRole(user.getRole());

        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
            encodePassword(existingUser); // Encodes password
        }

        return userRepo.save(existingUser);
    }

    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }

    /**
     * Encodes a plaintext password using Bcrypt hash function.
     *
     * @param user the uses that will have their password encoded.
     */
    private void encodePassword(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
        user.setPassword(encoder.encode(user.getPassword()));
    }
}
