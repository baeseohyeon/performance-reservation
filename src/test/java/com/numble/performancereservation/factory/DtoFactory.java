package com.numble.performancereservation.factory;

import com.numble.performancereservation.payment.PaymentDto;
import com.numble.performancereservation.payment.PaymentMethod;
import com.numble.performancereservation.performance.PerformanceDto;
import com.numble.performancereservation.performance.SeatDto;
import com.numble.performancereservation.performance.SeatType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DtoFactory {

    public static PerformanceDto createPerformanceDto(Long venueId, int capacity,
        LocalDateTime startTime, LocalDateTime endTime,
        String name, int normalPrice, int vipPrice) {
        PerformanceDto performanceDto = new PerformanceDto();
        performanceDto.setVenueId(venueId);
        performanceDto.setCapacity(capacity);
        performanceDto.setStartTime(startTime);
        performanceDto.setEndTime(endTime);
        performanceDto.setName(name);
        performanceDto.setNormalPrice(normalPrice);
        performanceDto.setVipPrice(vipPrice);
        return performanceDto;
    }

    public static PaymentDto createPaymentDto() {
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "1234-1234-1234-1234";
        String cardExpiration = "12/24";
        int cardCVV = 123;
        return new PaymentDto(paymentMethod, cardNumber, cardExpiration, cardCVV);
    }

    public static List<SeatDto> createReservationSeatDtoList() {
        List<SeatDto> seats = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            seats.add(new SeatDto((long) i, "A" + i, SeatType.VIP));
        }
        return seats;
    }

    public static List<SeatDto> createSeatDtoList() {
        List<SeatDto> seats = new ArrayList<>();
        int size = 10000;
        for (int i = 0; i < size; i++) {
            seats.add(new SeatDto(Integer.toString(i), SeatType.NORMAL));
        }
        return seats;
    }
}
