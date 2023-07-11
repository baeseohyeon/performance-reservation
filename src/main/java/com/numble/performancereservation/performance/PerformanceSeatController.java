package com.numble.performancereservation.performance;

import com.numble.performancereservation.common.ApiResult;
import com.numble.performancereservation.seat.SeatDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/performances")
@RestController
public class PerformanceSeatController {

    private final PerformanceSeatQueryService performanceSeatQueryService;

    @GetMapping("/{performanceId}/seats")
    public ApiResult<List<SeatDto>> findAvailableSeats(@PathVariable Long performanceId) {
        List<SeatDto> seats = performanceSeatQueryService.findAvailableSeatsByPerformanceId(
            performanceId).stream().map(SeatDto::new).collect(Collectors.toList());
        return new ApiResult<>(seats.size(), seats);
    }
}
