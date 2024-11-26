package com.agency.travel_api.controllers;

import com.agency.travel_api.dtos.DestinationDto;
import com.agency.travel_api.models.Destination;
import com.agency.travel_api.services.DestinationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationController {

    private final DestinationService service;

    public DestinationController(DestinationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Destination> create(@Valid @RequestBody DestinationDto dto) {
        Destination destination = new Destination(null, dto.getName(), dto.getLocation(), dto.getDescription(), 0.0, 0, 0);
        return ResponseEntity.ok(service.create(destination));
    }
    @PostMapping("/{id}/reserve")
    public ResponseEntity<Destination> reserveDestination(@PathVariable Long id) {
        return service.reserveDestination(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<Destination>> createMultiple(@Valid @RequestBody List<DestinationDto> dtoList) {
        List<Destination> destinations = dtoList.stream()
                .map(dto -> new Destination(null, dto.getName(), dto.getLocation(), dto.getDescription(), 0.0, 0, 0))
                .toList();
        List<Destination> createdDestinations = destinations.stream()
                .map(service::create)
                .toList();
        return ResponseEntity.ok(createdDestinations);
    }


    @GetMapping
    public ResponseEntity<List<Destination>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Destination>> searchDestinations(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String location) {
        return ResponseEntity.ok(service.searchByNameOrLocation(name, location));
    }

    @PatchMapping("/{id}/rating")
    public ResponseEntity<Destination> addRating(@PathVariable Long id, @RequestParam double rating) {
        return service.addRating(id, rating)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> update(@PathVariable Long id, @Valid @RequestBody DestinationDto dto) {
        return service.update(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDestination(@PathVariable Long id) {
        boolean isDeleted = service.delete(id);
        if (isDeleted) {
            return ResponseEntity.ok("Destination with ID " + id + " has been deleted.");
        } else {
            return ResponseEntity.status(404).body("Destination with ID " + id + " not found.");
        }
    }
}
