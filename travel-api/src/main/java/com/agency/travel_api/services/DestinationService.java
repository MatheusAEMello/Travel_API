package com.agency.travel_api.services;

import com.agency.travel_api.dtos.DestinationDto;
import com.agency.travel_api.models.Destination;
import com.agency.travel_api.repositories.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    private final DestinationRepository repository;

    public DestinationService(DestinationRepository repository) {
        this.repository = repository;
    }

    public Destination createDestination(DestinationDto dto) {
        Destination destination = new Destination(null, dto.getName(), dto.getLocation(), dto.getDescription(), 0.0, 0, 0);
        return repository.save(destination);
    }

    public List<Destination> createMultipleDestinations(List<DestinationDto> dtoList) {
        List<Destination> destinations = dtoList.stream()
                .map(dto -> new Destination(null, dto.getName(), dto.getLocation(), dto.getDescription(), 0.0, 0, 0))
                .collect(Collectors.toList());
        return destinations.stream()
                .map(repository::save)
                .collect(Collectors.toList());
    }

    public List<Destination> getAll() {
        return repository.findAll();
    }

    public Optional<Destination> getById(Long id) {
        return repository.findById(id);
    }

    public List<Destination> searchByNameOrLocation(String name, String location) {
        return repository.searchByNameOrLocation(name, location);
    }

    public Optional<Destination> reserveDestination(Long id) {
        return repository.findById(id).map(destination -> {
            destination.setTotalReservations(destination.getTotalReservations() + 1);
            return repository.save(destination);
        });
    }

    public Optional<Destination> update(Long id, DestinationDto dto) {
        return repository.findById(id).map(existingDestination -> {
            existingDestination.setName(dto.getName());
            existingDestination.setLocation(dto.getLocation());
            existingDestination.setDescription(dto.getDescription());
            return repository.save(existingDestination);
        });
    }

    public Optional<Destination> addRating(Long id, double rating) {
        return repository.findById(id).map(destination -> {
            double newAverage = ((destination.getAverageRating() * destination.getTotalRatings()) + rating) /
                    (destination.getTotalRatings() + 1);
            destination.setAverageRating(newAverage);
            destination.setTotalRatings(destination.getTotalRatings() + 1);
            return repository.save(destination);
        });
    }

    public boolean delete(Long id) {
        return repository.deleteById(id);
    }
}
