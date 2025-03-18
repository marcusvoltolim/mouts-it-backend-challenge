package com.moutsit.challenge.sqs;

import com.moutsit.challenge.model.dtos.OrderCreateDto;
import com.moutsit.challenge.services.OrderService;
import io.awspring.cloud.sqs.annotation.SqsListener;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
class OrderSqsListener {

    private static final String ORDER_OUTPUT_QUEUE = "order-output";

    private final OrderService orderService;
    private final SqsTemplate sqsTemplate;

    @SqsListener("order-input")
    void listen(final OrderCreateDto order, @Header("id") String id) {
        orderService.create(order)
            .doFirst(() -> log.info("Receiving message with for id {}", id))
            .doOnNext( orderOutput -> sqsTemplate.send(ORDER_OUTPUT_QUEUE, orderOutput))
            .subscribe();
    }

}
