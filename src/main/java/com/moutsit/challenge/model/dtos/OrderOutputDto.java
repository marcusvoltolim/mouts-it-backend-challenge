package com.moutsit.challenge.model.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record OrderOutputDto(UUID id, BigDecimal total, List<ProductDto> products) { }
