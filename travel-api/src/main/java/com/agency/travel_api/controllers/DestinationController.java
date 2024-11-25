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
        Destination destination = new Destination(null, dto.getName(), dto.getLocation(), dto.getDescription(), 0, 0);
        return ResponseEntity.ok(service.create(destination));
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

    @PatchMapping("/{id}/rating")
    public ResponseEntity<Destination> addRating(@PathVariable Long id, @RequestParam double rating) {
        return service.addRating(id, rating)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}
