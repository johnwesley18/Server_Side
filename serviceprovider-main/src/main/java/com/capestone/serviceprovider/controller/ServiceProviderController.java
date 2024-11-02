package com.capestone.serviceprovider.controller;

import com.capestone.serviceprovider.dto.ServiceProviderDTO;
import com.capestone.serviceprovider.service.ServiceProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/provider")
@CrossOrigin(origins = "http://localhost:3000")
public class ServiceProviderController {

    @Autowired
    private ServiceProviderService serviceProviderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ServiceProviderDTO> createServiceProvider(@RequestBody ServiceProviderDTO serviceProviderDTO) {
        return serviceProviderService.createServiceProvider(serviceProviderDTO);
    }
    @PutMapping("/changestatus/{sid}")
    public Mono<ServiceProviderDTO> updateServiceProviderstatus(@PathVariable String sid) {
        return serviceProviderService.updateServiceProviderstatus(sid);
    }

    @GetMapping("/{sid}")
    public Mono<ServiceProviderDTO> getServiceProviderById(@PathVariable String sid) {
        return serviceProviderService.getServiceProviderById(sid);
    }

    @PutMapping("/{sid}")
    public Mono<ServiceProviderDTO> updateServiceProvider(@PathVariable String sid, @RequestBody ServiceProviderDTO serviceProviderDTO) {
        return serviceProviderService.updateServiceProvider(sid, serviceProviderDTO);
    }

    @DeleteMapping("/{sid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteServiceProvider(@PathVariable String sid) {
        return serviceProviderService.deleteServiceProvider(sid);
    }

    @GetMapping
    public Flux<ServiceProviderDTO> getAllServiceProviders() {
        return serviceProviderService.getAllServiceProviders();
    }

    @GetMapping("/city/{city}")
    public Flux<ServiceProviderDTO> getServiceProvidersByCity(@PathVariable String city) {
        return serviceProviderService.getServiceProvidersByCity(city);
    }




    @GetMapping("/plumbers")
    public Flux<ServiceProviderDTO> getPlumbers() {
        return serviceProviderService.getAllServiceProviders().filter(serviceProvider -> serviceProvider.getServiceType().equals("Plumber"));
    }
    @GetMapping("/carpenters")
    public Flux<ServiceProviderDTO> getCarpenters() {
        return serviceProviderService.getAllServiceProviders().filter(serviceProvider -> serviceProvider.getServiceType().equals("Carpenter"));
    }
    @GetMapping("/tutors")
    public Flux<ServiceProviderDTO> getTutors() {
        return serviceProviderService.getAllServiceProviders().filter(serviceProvider -> serviceProvider.getServiceType().equals("Tutor"));
    }
    @GetMapping("/electricians")
    public Flux<ServiceProviderDTO> getElectricians() {
        return serviceProviderService.getAllServiceProviders().filter(serviceProvider -> serviceProvider.getServiceType().equals("Electrician"));
    }
    @GetMapping("/saloon")
    public Flux<ServiceProviderDTO> getCleaners() {
        return serviceProviderService.getAllServiceProviders().filter(serviceProvider -> serviceProvider.getServiceType().equals("Saloon"));
    }
}