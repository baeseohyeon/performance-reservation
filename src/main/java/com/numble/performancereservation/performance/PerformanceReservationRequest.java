package com.numble.performancereservation.performance;

import com.numble.performancereservation.payment.PaymentDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PerformanceReservationRequest {

    private Long performanceId;
    int totalPrice;
    PaymentDto paymentDto;
    private List<SeatDto> seats;
}
