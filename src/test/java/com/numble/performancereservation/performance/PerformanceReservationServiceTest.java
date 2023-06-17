package com.numble.performancereservation.performance;

import static org.assertj.core.api.Assertions.*;

import com.numble.performancereservation.payment.PaymentDto;
import com.numble.performancereservation.payment.PaymentMethod;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class PerformanceReservationServiceTest {

    @Autowired
    PerformanceReservationService performanceReservationService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    SeatService seatService;

    @Test
    void reservationPerformance() {
        //given
        Long performanceId = 1L;
        int totalPrice = 200000;
        PaymentDto paymentDto = createPaymentDto();
        List<SeatDto> seats = createReservationSeatDtoList();
        PerformanceReservationRequest request = new PerformanceReservationRequest(performanceId,
            totalPrice, paymentDto, seats);
        Long userId = 1L;

        //when
        Reservation reservation = performanceReservationService.reservationPerformance(request,
            userId);

        //then
        Reservation findReservation = reservationService.findById(reservation.getId());
        assertThat(reservation.getId()).isEqualTo(findReservation.getId());

        long reservedSeatCount = seatService.countReservedSeatByIdIn(
            seats.stream().map(SeatDto::getSeatId).collect(Collectors.toList()));
        assertThat(reservedSeatCount).isEqualTo(seats.size());
    }

    @Test
    void reservationPerformance_alreadyReservedSeat() {
        //given
        Long performanceId = 1L;
        int totalPrice = 200000;
        PaymentDto paymentDto = createPaymentDto();
        List<SeatDto> seats = createReservationSeatDtoList();
        PerformanceReservationRequest request = new PerformanceReservationRequest(performanceId,
            totalPrice, paymentDto, seats);
        Long userId = 1L;
        performanceReservationService.reservationPerformance(request, userId);

        //when
        seats.clear();
        seats.add(new SeatDto(1L, "A1", SeatType.VIP));
        PerformanceReservationRequest reservedSeatRequest = new PerformanceReservationRequest(
            performanceId, totalPrice, paymentDto, seats);

        //then
        assertThatThrownBy(
            () -> performanceReservationService.reservationPerformance(reservedSeatRequest, userId))
            .isInstanceOf(IllegalArgumentException.class);
    }

    PaymentDto createPaymentDto() {
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "1234-1234-1234-1234";
        String cardExpiration = "12/24";
        int cardCVV = 123;
        return new PaymentDto(paymentMethod, cardNumber, cardExpiration, cardCVV);
    }

    List<SeatDto> createReservationSeatDtoList() {
        List<SeatDto> seats = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            seats.add(new SeatDto((long) i, "A" + i, SeatType.VIP));
        }
        return seats;
    }
}
