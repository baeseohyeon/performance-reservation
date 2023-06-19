package com.numble.performancereservation.performance;

import static com.numble.performancereservation.factory.DtoFactory.createPaymentDto;
import static com.numble.performancereservation.factory.DtoFactory.createReservationSeatDtoList;
import static org.assertj.core.api.Assertions.*;

import com.numble.performancereservation.payment.PaymentDto;
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
}
