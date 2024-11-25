package com.agency.travel_api.models;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Destination {

    private Long id;

    @NotBlank(message = "O nome do destino é obrigatório.")
    private String name;

    @NotBlank(message = "A localização é obrigatória.")
    private String location;

    @Size(max = 500, message = "A descrição pode ter no máximo 500 caracteres.")
    private String description;

    @Min(value = 0, message = "A avaliação não pode ser negativa.")
    @Max(value = 10, message = "A avaliação não pode exceder 10.")
    private double averageRating;

    private int totalRatings;
}
