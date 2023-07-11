package com.numble.performancereservation.performance;

import static com.numble.performancereservation.factory.DtoFactory.createPaymentDto;
import static com.numble.performancereservation.factory.DtoFactory.createReservationSeatDtoList;
import static org.assertj.core.api.Assertions.*;

import com.numble.performancereservation.payment.PaymentDto;
import com.numble.performancereservation.reservation.Reservation;
import com.numble.performancereservation.reservation.ReservationSeatService;
import com.numble.performancereservation.reservation.ReservationService;
import com.numble.performancereservation.seat.SeatDto;
import com.numble.performancereservation.seat.SeatService;
import java.util.List;
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
    @Autowired
    ReservationSeatService reservationSeatService;

    @Test
    void reservationPerformance() {
        //given
        Long performanceId = 1L;
        int totalPrice = 200000;
        PaymentDto paymentDto = createPaymentDto();
        List<SeatDto> seats = createReservationSeatDtoList(List.of(4));
        PerformanceReservationRequest request = new PerformanceReservationRequest(performanceId,
            totalPrice, paymentDto, seats);
        Long userId = 1L;

        //when
        Reservation reservation = performanceReservationService.reservationPerformance(request,
            userId);

        //then
        Reservation findReservation = reservationService.findById(reservation.getId());
        assertThat(reservation.getId()).isEqualTo(findReservation.getId());

        long reservedSeatCount = reservationSeatService.countByReservation(findReservation);
        assertThat(reservedSeatCount).isEqualTo(seats.size());
    }

    @Test
    void reservationPerformance_alreadyReservedSeat() {
        //given
        Long performanceId = 1L;
        int totalPrice = 200000;
        PaymentDto paymentDto = createPaymentDto();
        List<SeatDto> seats = createReservationSeatDtoList(List.of(5,6));
        PerformanceReservationRequest request = new PerformanceReservationRequest(performanceId,
            totalPrice, paymentDto, seats);
        Long userId = 1L;
        performanceReservationService.reservationPerformance(request, userId);

        //when
        seats = createReservationSeatDtoList(List.of(5));
        PerformanceReservationRequest reservedSeatRequest = new PerformanceReservationRequest(
            performanceId, totalPrice, paymentDto, seats);

        //then
        assertThatThrownBy(
            () -> performanceReservationService.reservationPerformance(reservedSeatRequest, userId))
            .isInstanceOf(IllegalArgumentException.class);
    }
}
