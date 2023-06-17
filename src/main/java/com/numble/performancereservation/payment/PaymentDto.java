package com.numble.performancereservation.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private PaymentMethod paymentMethod;
    private String cardNumber;
    private String cardExpiration;
    private int cardCVV;
}
