package com.moutsit.challenge.repositories;

import java.util.UUID;
import com.moutsit.challenge.model.entities.OrderEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends ReactiveMongoRepository<OrderEntity, UUID> { }
