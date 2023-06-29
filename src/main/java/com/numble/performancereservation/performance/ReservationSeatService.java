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

    public void saveAll(List<SeatDto> seats, Reservation reservation) {
        reservationSeatJdbcRepository.saveAll(seats, reservation.getId());
    }
}
