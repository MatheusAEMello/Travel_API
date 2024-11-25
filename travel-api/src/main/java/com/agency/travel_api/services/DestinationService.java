package com.agency.travel_api.services;

import com.agency.travel_api.models.Destination;
import com.agency.travel_api.repositories.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository repository;

    public DestinationService(DestinationRepository repository) {
        this.repository = repository;
    }

    public Destination create(Destination destination) {
        return repository.save(destination);
    }

    public List<Destination> getAll() {
        return repository.findAll();
    }

    public Optional<Destination> getById(Long id) {
        return repository.findById(id);
    }

    public boolean delete(Long id) {
        return repository.deleteById(id);
    }

    public Optional<Destination> addRating(Long id, double rating) {
        return repository.findById(id).map(destination -> {
            double newAverage = ((destination.getAverageRating() * destination.getTotalRatings()) + rating) /
                    (destination.getTotalRatings() + 1);
            destination.setAverageRating(newAverage);
            destination.setTotalRatings(destination.getTotalRatings() + 1);
            return destination;
        });
    }
}
