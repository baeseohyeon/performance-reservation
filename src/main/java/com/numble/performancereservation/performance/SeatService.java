package com.numble.performancereservation.performance;

import static com.numble.performancereservation.exception.ExceptionMessage.ALREADY_RESERVED_SEAT;
import static com.numble.performancereservation.exception.ExceptionMessage.INVALID_SEAT_ID;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SeatService {

    private final SeatRepository seatRepository;
    private final SeatJdbcRepository seatJdbcRepository;

    public void saveAll(List<SeatDto> seats, Long venueId) {
        seatJdbcRepository.saveAll(seats, venueId);
    }

    public int countByVenueId(Long venueId) {
        return seatRepository.countByVenueId(venueId);
    }

    public void validateSeatExistenceAndReservation(List<Long> seatIds) {
        validateSeatAlreadyReserved(seatIds);
        validateExistingSeat(seatIds);
    }

    private void validateSeatAlreadyReserved(List<Long> seatIds) {
        if (seatRepository.existsByIdInAndIsReserved(seatIds, true)) {
            throw new IllegalArgumentException(ALREADY_RESERVED_SEAT);
        }
    }

    private void validateExistingSeat(List<Long> seatIds) {
        if (seatRepository.countByIdIn(seatIds) != seatIds.size()) {
            throw new IllegalArgumentException(INVALID_SEAT_ID);
        }
    }

    public int reservationSeat(List<Long> seatIds, Reservation reservation) {
        return seatRepository.updateIsReservedToTrueAndReservationByIdIn(seatIds, reservation);
    }

    public long countReservedSeatByIdIn(List<Long> seatIds) {
        return seatRepository.countByIdInAndIsReserved(seatIds, true);
    }

    public List<Seat> findSeatsByVenueId(Long venueId){
        return seatRepository.findByVenueId(venueId);
    }
}
