package com.agency.travel_api.repositories;

import com.agency.travel_api.models.Destination;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DestinationRepository {

    private final List<Destination> destinations = new ArrayList<>();
    private Long nextId = 1L;

    public Destination save(Destination destination) {
        if (destination.getId() == null) {
            destination.setId(nextId++);
            destination.setAverageRating(0.0);
            destination.setTotalRatings(0);
            destinations.add(destination);
        } else {
            deleteById(destination.getId());
            destinations.add(destination);
        }
        return destination;
    }

    public List<Destination> findAll() {
        return new ArrayList<>(destinations);
    }

    public Optional<Destination> findById(Long id) {
        return destinations.stream()
                .filter(destination -> destination.getId().equals(id))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        return destinations.removeIf(destination -> destination.getId().equals(id));
    }

    public boolean existsById(Long id) {
        return destinations.stream()
                .anyMatch(destination -> destination.getId().equals(id));
    }
}