package com.numble.performancereservation.payment;

import com.numble.performancereservation.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Payment extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String cardExpiration;
    @Column(name = "card_cvv")
    private int cardCVV;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Builder
    public Payment(String cardNumber, String cardExpiration, int cardCVV,
        PaymentMethod paymentMethod) {
        this.cardNumber = cardNumber;
        this.cardExpiration = cardExpiration;
        this.cardCVV = cardCVV;
        this.paymentMethod = paymentMethod;
    }
}
