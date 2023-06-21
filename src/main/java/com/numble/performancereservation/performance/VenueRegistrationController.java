package com.numble.performancereservation.performance;

import static org.springframework.http.HttpStatus.CREATED;

import com.numble.performancereservation.user.security.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/venues")
@RestController
public class VenueRegistrationController {

    private final VenueRegistrationService venueRegistrationService;

    @ResponseStatus(CREATED)
    @PostMapping
    public void registrationVenue(@RequestBody VenueDto venueDto,
        @AuthenticationPrincipal CustomUser user) {
        venueRegistrationService.registrationVenue(venueDto, user.getUserId());
    }
}
