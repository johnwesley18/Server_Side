package com.capestone.users.controller;
import com.capestone.users.dto.UserDTO;
import com.capestone.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/get/{uid}")
    public Mono<UserDTO> getUserById(@PathVariable String uid) {
        return userService.getUserById(uid);
    }

    @PutMapping("/update/{uid}")
    public Mono<UserDTO> updateUser(@PathVariable String uid, @RequestBody UserDTO userDTO) {
        return userService.updateUser(uid, userDTO);
    }

    @DeleteMapping("/delete/{uid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUser(@PathVariable String uid) {
        return userService.deleteUser(uid);
    }

    @GetMapping("/getall")
    public Flux<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
