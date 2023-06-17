package com.numble.performancereservation.performance;

import static com.numble.performancereservation.exception.ExceptionMessage.*;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class PerformancePrice {

    private int normalPrice;
    private int vipPrice;

    public PerformancePrice(int normalPrice, int vipPrice) {
        validatePrice(normalPrice, vipPrice);
        this.normalPrice = normalPrice;
        this.vipPrice = vipPrice;
    }

    private void validatePrice(int normalPrice, int vipPrice) {
        if (normalPrice > vipPrice) {
            throw new IllegalArgumentException(VIP_SEAT_PRICE_CANNOT_BE_CHEAPER_THAN_NORMAL);
        }
        if (normalPrice < 0) {
            throw new IllegalArgumentException(SEAT_PRICE_CANNOT_BE_NEGATIVE);
        }
    }
}
