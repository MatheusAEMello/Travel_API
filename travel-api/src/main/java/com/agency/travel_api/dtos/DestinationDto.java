package com.agency.travel_api.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DestinationDto {

    @NotBlank(message = "O nome do destino é obrigatório.")
    private String name;

    @NotBlank(message = "A localização é obrigatória.")
    private String location;

    @Size(max = 500, message = "A descrição pode ter no máximo 500 caracteres.")
    private String description;
}
