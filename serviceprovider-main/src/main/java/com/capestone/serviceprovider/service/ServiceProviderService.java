package com.capestone.serviceprovider.service;

import com.capestone.serviceprovider.dto.ServiceProviderDTO;
import com.capestone.serviceprovider.model.ServiceProvider;
import com.capestone.serviceprovider.repository.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class ServiceProviderService {
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public Mono<ServiceProviderDTO> createServiceProvider(ServiceProviderDTO dto) {
        dto.setStatus(ServiceProvider.ProviderStatus.PENDING);
        ServiceProvider entity = convertToEntity(dto);
        return serviceProviderRepository.save(entity).map(this::convertToDTO);
    }

    public Mono<ServiceProviderDTO> getServiceProviderById(String sid) {
        return serviceProviderRepository.findById(sid).map(this::convertToDTO);
    }

    public Mono<ServiceProviderDTO> updateServiceProvider(String sid, ServiceProviderDTO dto) {
        return serviceProviderRepository.findById(sid)
                .flatMap(existing -> {
                    existing.setName(dto.getName());
                    existing.setEmail(dto.getEmail());
                    existing.setPassword(dto.getPassword());
                    existing.setRating(dto.getRating());
                    existing.setExperience(dto.getExperience());
                    existing.setCity(dto.getCity());
                    existing.setDescription(dto.getDescription());
                    existing.setServiceCost(dto.getServiceCost());
                    existing.setProfileImg(dto.getProfileImg());
                    existing.setServiceType(dto.getServiceType());
                    existing.setWorking(dto.getWorking());
                    existing.setStatus(dto.getStatus());
                    return serviceProviderRepository.save(existing);
                })
                .map(this::convertToDTO);
    }

    public Mono<Void> deleteServiceProvider(String sid) {
        return serviceProviderRepository.deleteById(sid);
    }

    public Flux<ServiceProviderDTO> getAllServiceProviders() {
        return serviceProviderRepository.findAll().map(this::convertToDTO);
    }

    public Flux<ServiceProviderDTO> getServiceProvidersByCity(String city) {
        return serviceProviderRepository.findByCity(city).map(this::convertToDTO);
    }

    private ServiceProvider convertToEntity(ServiceProviderDTO dto) {
        ServiceProvider entity = new ServiceProvider();

        entity.setSid(dto.getSid());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setRating(dto.getRating());
        entity.setExperience(dto.getExperience());
        entity.setCity(dto.getCity());
        entity.setDescription(dto.getDescription());
        entity.setServiceCost(dto.getServiceCost());
        entity.setProfileImg(dto.getProfileImg());
        entity.setServiceType(dto.getServiceType());
        entity.setWorking(dto.getWorking());
        entity.setStatus(dto.getStatus());

        return entity;
    }

    private ServiceProviderDTO convertToDTO(ServiceProvider entity) {
        ServiceProviderDTO dto = new ServiceProviderDTO();

        dto.setSid(entity.getSid());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setRating(entity.getRating());
        dto.setExperience(entity.getExperience());
        dto.setCity(entity.getCity());
        dto.setDescription(entity.getDescription());
        dto.setServiceCost(entity.getServiceCost());
        dto.setProfileImg(entity.getProfileImg());
        dto.setServiceType(entity.getServiceType());
        dto.setWorking(entity.getWorking());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    public Mono<ServiceProviderDTO> updateServiceProviderstatus(String sid) {
        return serviceProviderRepository.findById(sid)
               .flatMap(existing -> {
                    existing.setStatus(ServiceProvider.ProviderStatus.ACCEPTED);
                    return serviceProviderRepository.save(existing);
                })
               .map(this::convertToDTO);


    }
}
