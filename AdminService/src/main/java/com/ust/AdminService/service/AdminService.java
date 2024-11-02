package com.ust.AdminService.service;


import com.ust.AdminService.model.Admin;
import com.ust.AdminService.repo.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<Admin> addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Flux<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Mono<Boolean> validateAdmin(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password)
                .map(admin -> true)
                .defaultIfEmpty(false);
    }

    public Flux<Object> listServiceProviders() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8088/provider")
                .retrieve()
                .bodyToFlux(Object.class);
    }

    public Flux<Object> listUsers() {
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8088/user/getall")
                .retrieve()
                .bodyToFlux(Object.class);
    }

    public Mono<Object> updateProviderStatus(String sid) {
        return webClientBuilder.build()
                .put()
                .uri("http://localhost:8088/provider/changestatus/" + sid)
                .retrieve()
                .bodyToMono(Object.class);
    }

    public Mono<Void> deleteServiceProvider(String sid) {
        return webClientBuilder.build()
                .delete()
                .uri("http://localhost:8088/provider/" + sid)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Void> deleteUser(String uid) {
        return webClientBuilder.build()
                .delete()
                .uri("http://localhost:8087/user/delete/" + uid)
                .retrieve()
                .bodyToMono(Void.class);
    }

    public Mono<Admin> getAdminById(String aid) {
        return adminRepository.findById(aid);
    }
}
