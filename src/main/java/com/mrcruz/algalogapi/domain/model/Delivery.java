package com.mrcruz.algalogapi.domain.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    BigDecimal fee;
    LocalDateTime orderedDate;
    LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    DeliveryStatus deliveryStatus;

    @ManyToOne
    Client client;

    @Embedded
    Addressee addressee;

}
