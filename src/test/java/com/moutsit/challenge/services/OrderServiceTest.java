package com.moutsit.challenge.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moutsit.challenge.model.dtos.OrderCreateDto;
import com.moutsit.challenge.model.dtos.OrderOutputDto;
import com.moutsit.challenge.model.dtos.ProductDto;
import com.moutsit.challenge.model.entities.OrderEntity;
import com.moutsit.challenge.model.entities.ProductEntity;
import com.moutsit.challenge.repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    private static final UUID ORDER_ID = new UUID(0, 0);

    @Mock private OrderRepository orderRepository;
    @Spy private ObjectMapper objectMapper;
    @InjectMocks private OrderService orderService;

    @Test
    void create() {
        final var order = new OrderCreateDto(ORDER_ID, List.of(
            new ProductDto("Book-1", new BigDecimal("10.00")),
            new ProductDto("Book-2", new BigDecimal("25.00")),
            new ProductDto("Book-3", new BigDecimal("15.00"))));

        when(orderRepository.save(any(OrderEntity.class)))
            .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        orderService.create(order)
            .as(StepVerifier::create)
            .expectNext(new OrderOutputDto(ORDER_ID, new BigDecimal("50.00"), null))
            .verifyComplete();
    }

    @Test
    void findAll() {
        when(orderRepository.findAll())
            .thenReturn(Flux.just(
                new OrderEntity(ORDER_ID, List.of(new ProductEntity("Book-1", new BigDecimal("50.00"))), new BigDecimal("50.00")),
                new OrderEntity(new UUID(1, 1), List.of(new ProductEntity("Book-2", new BigDecimal("10.00"))), new BigDecimal("10.00"))
            ));

        orderService.findAll()
            .as(StepVerifier::create)
            .expectNext(new OrderOutputDto(ORDER_ID, new BigDecimal("50.00"), List.of(new ProductDto("Book-1", new BigDecimal("50.00")))))
            .expectNext(new OrderOutputDto(new UUID(1, 1), new BigDecimal("10.00"), List.of(new ProductDto("Book-2", new BigDecimal("10.00")))))
            .verifyComplete();
    }

    @Test
    void findById() {
        when(orderRepository.findById(ORDER_ID))
            .thenReturn(Mono.just(new OrderEntity(ORDER_ID, List.of(new ProductEntity("Book-1", new BigDecimal("50.00"))), new BigDecimal("50.00"))));

        orderService.findById(ORDER_ID)
            .as(StepVerifier::create)
            .expectNext(new OrderOutputDto(ORDER_ID, new BigDecimal("50.00"), List.of(new ProductDto("Book-1", new BigDecimal("50.00")))))
            .verifyComplete();
    }

}
