package com.capestone.users.service;
import com.capestone.users.dto.UserDTO;
import com.capestone.users.model.User;
import com.capestone.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Mono<UserDTO> createUser(UserDTO userDTO) {
        //userDTO.setProfileImg("https://freesvg.org/img/abstract-user-flat-4.png");
        userDTO.setEmail(userDTO.getEmail().toLowerCase());
        User user = convertToEntity(userDTO);
        return userRepository.save(user).map(this::convertToDTO);
    }

    public Mono<UserDTO> getUserById(String uid) {
        return userRepository.findById(uid).map(this::convertToDTO);
    }

    public Mono<UserDTO> updateUser(String uid, UserDTO userDTO) {
        return userRepository.findById(uid)
                .flatMap(existingUser -> {
                    existingUser.setUsername(userDTO.getUsername());
                    existingUser.setEmail(userDTO.getEmail());
                    existingUser.setPassword(userDTO.getPassword());
                    existingUser.setAddress(userDTO.getAddress());
                    existingUser.setContact(userDTO.getContact());
                    existingUser.setProfileImg(userDTO.getProfileImg());
                    return userRepository.save(existingUser);
                })
                .map(this::convertToDTO);
    }

    public Mono<Void> deleteUser(String uid) {
        return userRepository.deleteById(uid);
    }

    public Flux<UserDTO> getAllUsers() {
        return userRepository.findAll().map(this::convertToDTO);
    }

    private User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUid(userDTO.getUid());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setContact(userDTO.getContact());
        user.setProfileImg(userDTO.getProfileImg());
        return user;
    }

    private UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUid(user.getUid());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAddress(user.getAddress());
        userDTO.setContact(user.getContact());
        userDTO.setProfileImg(user.getProfileImg());
        return userDTO;
    }
}
