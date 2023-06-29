package com.numble.performancereservation.performance;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ReservationSeatService {

    private final ReservationSeatJdbcRepository reservationSeatJdbcRepository;
    private final ReservationSeatRepository reservationSeatRepository;

    public void saveAll(List<Long> seatIds, Reservation reservation) {
        reservationSeatJdbcRepository.saveAll(seatIds, reservation.getId());
    }

    public long countByReservation(Reservation reservation) {
        return reservationSeatRepository.countByReservation(reservation);
    }
}
