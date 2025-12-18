package com.example.mymy.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class Orders {
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private LocalDateTime orderDate;

    private Integer totalPrice;

    private String receiveName;

    private String receiveAddress;

    @Enumerated(EnumType.STRING)
    private Status status;
}
