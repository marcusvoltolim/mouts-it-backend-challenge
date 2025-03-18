package com.moutsit.challenge.services;

import java.math.BigDecimal;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moutsit.challenge.model.dtos.OrderCreateDto;
import com.moutsit.challenge.model.dtos.OrderOutputDto;
import com.moutsit.challenge.model.dtos.ProductDto;
import com.moutsit.challenge.model.entities.OrderEntity;
import com.moutsit.challenge.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;

    public Mono<OrderOutputDto> create(final OrderCreateDto order) {
        return Mono.just(objectMapper.convertValue(order, OrderEntity.class))
            .doFirst(() -> log.info("Creating order for id {}", order.id()))
            .doOnNext(entity -> {
                final var total = order.products().stream().map(ProductDto::price).reduce(BigDecimal.ZERO, BigDecimal::add);
                entity.setTotal(total);
            })
            .flatMap(orderRepository::save)
            .map(entity -> new OrderOutputDto(entity.getId(), entity.getTotal(), null));
    }

    public Flux<OrderOutputDto> findAll() {
        return orderRepository.findAll()
            .map(entity -> objectMapper.convertValue(entity, OrderOutputDto.class));
    }

    public Mono<OrderOutputDto> findById(final UUID id) {
        return orderRepository.findById(id)
            .map(entity -> objectMapper.convertValue(entity, OrderOutputDto.class));
    }

}
