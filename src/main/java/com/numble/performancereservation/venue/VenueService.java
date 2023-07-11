package com.numble.performancereservation.venue;

import static com.numble.performancereservation.exception.ExceptionMessage.INVALID_VENUE_ID;

import com.numble.performancereservation.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class VenueService {

    private final VenueRepository venueRepository;

    public Venue save(User user, VenueDto venueDto) {
        Venue venue = Venue.builder()
            .user(user)
            .name(venueDto.getName())
            .capacity(venueDto.getCapacity())
            .type(venueDto.getVenueType())
            .possibleTime(venueDto.getPossibleTimes()).build();
        return venueRepository.save(venue);
    }

    public Venue findById(Long venueId) {
        return venueRepository.findById(venueId).orElseThrow(() -> new IllegalArgumentException(INVALID_VENUE_ID));
    }
}
