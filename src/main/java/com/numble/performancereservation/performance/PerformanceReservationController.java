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
@RequestMapping("/api/reservations")
@RestController
public class PerformanceReservationController {

    private final ConcurrentPerformanceReservationService performanceReservationService;

    @ResponseStatus(CREATED)
    @PostMapping
    public void performanceReservation(@RequestBody PerformanceReservationRequest request,
        @AuthenticationPrincipal CustomUser user) {
        performanceReservationService.reservationPerformance(request, user.getUserId());
    }
}
