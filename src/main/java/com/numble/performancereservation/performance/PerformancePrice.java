package com.numble.performancereservation.performance;

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
        this.normalPrice = normalPrice;
        this.vipPrice = vipPrice;
    }
}
