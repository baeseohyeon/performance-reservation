package com.numble.performancereservation.performance;

import com.numble.performancereservation.payment.Payment;
import com.numble.performancereservation.payment.PaymentService;
import com.numble.performancereservation.user.User;
import com.numble.performancereservation.user.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class PerformanceReservationService {

    private final ReservationService reservationService;
    private final SeatService seatService;
    private final PerformanceQueryService performanceQueryService;
    private final UserService userService;
    private final PaymentService paymentService;

    public Reservation reservationPerformance(PerformanceReservationRequest request, Long userId) {
        List<Long> seatIds = request.getSeatIds();
        seatService.validateSeatExistenceAndReservation(seatIds);

        User user = userService.findById(userId);
        Performance performance = performanceQueryService.findById(request.getPerformanceId());
        performance.decreaseAvailableSeats(seatIds.size());
        Payment payment = paymentService.saveIfNotFound(request.getPaymentDto());
        paymentService.processPayment();

        Reservation reservation = reservationService.save(user, performance, payment);
        seatService.reservationSeat(seatIds, reservation);
        return reservation;
    }
}
