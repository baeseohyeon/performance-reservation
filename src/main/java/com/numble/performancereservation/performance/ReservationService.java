package com.numble.performancereservation.performance;

import static com.numble.performancereservation.exception.ExceptionMessage.ALREADY_RESERVED_SEAT;
import static com.numble.performancereservation.exception.ExceptionMessage.INVALID_RESERVATION_ID;

import com.numble.performancereservation.payment.Payment;
import com.numble.performancereservation.user.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public Reservation save(User user, Performance performance, Payment payment) {
        Reservation reservation = new Reservation(user, performance, payment);
        return reservationRepository.save(reservation);
    }

    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(INVALID_RESERVATION_ID));
    }

    public long countByPerformanceId(Long performanceId) {
        return reservationRepository.countByPerformanceId(performanceId);
    }

    public void validateSeatAlreadyReserved(Long performanceId, List<Long> seatIds) {
        if(reservationRepository.existsByReservationAndSeatIdIn(performanceId, seatIds)) {
            throw new IllegalArgumentException(ALREADY_RESERVED_SEAT);
        }
    }
}
