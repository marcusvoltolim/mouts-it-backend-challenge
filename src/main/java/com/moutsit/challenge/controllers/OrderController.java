package com.moutsit.challenge.controllers;

import java.net.URI;
import java.util.UUID;
import com.moutsit.challenge.model.dtos.OrderCreateDto;
import com.moutsit.challenge.model.dtos.OrderOutputDto;
import com.moutsit.challenge.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RequestMapping("order")
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    Mono<ResponseEntity<OrderOutputDto>> create(@RequestBody OrderCreateDto orderCreate) {
        return orderService.create(orderCreate)
            .map(output -> ResponseEntity.created(URI.create(output.id().toString())).body(output));
    }

    @GetMapping
    ResponseEntity<Flux<OrderOutputDto>> getAll() {
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/{referenceId}")
    ResponseEntity<Mono<OrderOutputDto>> getById(@PathVariable UUID referenceId) {
        return ResponseEntity.ok(orderService.findById(referenceId));
    }

}
