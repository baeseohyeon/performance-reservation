package com.numble.performancereservation.venue;

import static com.numble.performancereservation.factory.DtoFactory.createSeatDtoList;
import static org.assertj.core.api.Assertions.assertThat;

import com.numble.performancereservation.seat.SeatDto;
import com.numble.performancereservation.seat.SeatService;
import com.numble.performancereservation.venue.Venue;
import com.numble.performancereservation.venue.VenueDto;
import com.numble.performancereservation.venue.VenueRegistrationService;
import com.numble.performancereservation.venue.VenueType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class VenueRegistrationServiceTest {

    @Autowired
    VenueRegistrationService venueRegistrationService;
    @Autowired
    SeatService seatService;
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    void registrationVenue() {
        //given
        Long producerId = 1L;
        List<SeatDto> seats = createSeatDtoList();
        String venueName = "venue";
        int capacity = 10000;
        VenueType type = VenueType.FIXED_SEAT;
        String possibleTimes = "10:00-11:00";
        VenueDto venueDto = new VenueDto(venueName, capacity, type, possibleTimes, seats);

        //when
        long start = System.currentTimeMillis();
        Venue venue = venueRegistrationService.registrationVenue(venueDto, producerId);
        long end = System.currentTimeMillis();

        //then
        logger.info("batch insert execute time = {}", (end - start));
        assertThat(seatService.countByVenueId(venue.getId())).isEqualTo(seats.size());
    }
}
