package com.moutsit.challenge.model.dtos;

import java.util.List;
import java.util.UUID;

public record OrderCreateDto(UUID id, List<ProductDto> products) { }
