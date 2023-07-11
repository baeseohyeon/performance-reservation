package com.numble.performancereservation.venue;

import com.numble.performancereservation.seat.SeatService;
import com.numble.performancereservation.user.ProducerType;
import com.numble.performancereservation.user.User;
import com.numble.performancereservation.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class VenueRegistrationService {

    private final VenueService venueService;
    private final SeatService seatService;
    private final UserService userService;

    public Venue registrationVenue(VenueDto venueDto, Long userId) {
        User producer = userService.findProducerByIdAndType(userId, ProducerType.VENUE);
        Venue venue = venueService.save(producer, venueDto);
        seatService.saveAll(venueDto.getSeats(), venue.getId());
        return venue;
    }
}
