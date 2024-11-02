package com.ust.AdminService.controller;


import com.ust.AdminService.model.Admin;
import com.ust.AdminService.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/addadmin")
    public Mono<Admin> addAdmin(@RequestBody Admin admin) {
        return adminService.addAdmin(admin);
    }

    @GetMapping("/getalladmin")
    public Flux<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/validate/{email}/{password}")
    public Mono<Boolean> validateAdmin(@PathVariable String email, @PathVariable String password) {
        return adminService.validateAdmin(email, password);
    }
    @GetMapping("/get/{aid}")
    public Mono<Admin> getAdminById(@PathVariable String aid) {
        return adminService.getAdminById(aid);
    }

    @GetMapping("/list-of-providers")
    public Flux<Object> listServiceProviders() {
        return adminService.listServiceProviders();
    }

    @GetMapping("/list-of-users")
    public Flux<Object> listUsers() {
        return adminService.listUsers();
    }

    @PutMapping("/updatestatus/{sid}")
    public Mono<Object> updateProviderStatus(@PathVariable String sid) {
        return adminService.updateProviderStatus(sid);
    }

    @DeleteMapping("/deleteserviceprovider/{sid}")
    public Mono<Void> deleteServiceProvider(@PathVariable String sid) {
        return adminService.deleteServiceProvider(sid);
    }

    @DeleteMapping("/deleteuserprovider/{uid}")
    public Mono<Void> deleteUser(@PathVariable String uid) {
        return adminService.deleteUser(uid);
    }
}