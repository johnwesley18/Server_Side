package com.ust.API_Gateway.controller;

import com.ust.API_Gateway.model.LoginDTO;
import com.ust.API_Gateway.model.ServiceProvider;
import com.ust.API_Gateway.model.User;
import com.ust.API_Gateway.repo.UserRoleRepository;
import com.ust.API_Gateway.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ApiGatewayController {

    private final WebClient webClient;
    private final UserRoleRepository userRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ApiGatewayController(WebClient.Builder webClientBuilder, UserRoleRepository userRepository, JwtService jwtService) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8888").build();
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @GetMapping
    public String checkpoint(){
        return "Working GO Sleep ! :)";
    }

    @PostMapping("/login")
    public Mono<String> login(@RequestBody LoginDTO loginDto) {
        return userRepository.findByUsername(loginDto.getUsername())
                .flatMap(user -> {
                    if (passwordEncoder.matches(loginDto.getPassword(), user.getPasswordHash())) {
                        // Generate JWT token
                        String token = jwtService.generateToken(user);
                        return Mono.just(token);
                    } else {
                        return Mono.error(new RuntimeException("Invalid credentials"));
                    }
                })
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }



    @PostMapping("/register/user")
    public Mono<ResponseEntity<String>> registerUser(@RequestBody User registerCustomerDto) {
        User user = new User();
        user.setUsername(registerCustomerDto.getUsername());
        user.setPasswordHash(passwordEncoder.encode(registerCustomerDto.getPassword()));
        user.setRole("ROLE_USER");

        return userRepository.save(user)
                .then(Mono.just(ResponseEntity.ok("Registered")))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Registration failed")));
    }


    @PostMapping("/register/provider")
    public String registerVendor(@RequestBody ServiceProvider registerVendorDto) {
        User user = new User();
        user.setUsername(registerVendorDto.getName());
        user.setPasswordHash(passwordEncoder.encode(registerVendorDto.getPassword()));
        user.setRole("ROLE_SERVICE_PROVIDER");

        // Save user in the database
        userRepository.save(user);
        return "Registered";
        // Forward the remaining details to the Vendor microservice
//        return webClient.post()
//                .uri("/vendors/register")
//                .bodyValue(registerVendorDto)
//                .retrieve()
//                .toEntity(String.class);
    }
}
