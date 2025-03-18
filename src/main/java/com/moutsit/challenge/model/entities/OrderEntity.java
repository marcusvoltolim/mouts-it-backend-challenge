package com.moutsit.challenge.model.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class OrderEntity {

    @Id
    private UUID id;
    private List<ProductEntity> products;
    private BigDecimal total;

}
