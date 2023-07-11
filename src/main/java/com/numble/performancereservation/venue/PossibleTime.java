package com.numble.performancereservation.venue;

import static com.numble.performancereservation.exception.ExceptionMessage.INVALID_VENUE_USE_TIME;

import jakarta.persistence.Embeddable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class PossibleTime {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final int MIN_USE_HOUR = 1;
    private LocalTime startTime;
    private LocalTime endTime;

    public PossibleTime(String time) {
        String[] times = time.split("-");
        startTime = LocalTime.parse(times[0], formatter);
        endTime = LocalTime.parse(times[1], formatter);
        validateTime(startTime, endTime);
    }

    private void validateTime(LocalTime startTime, LocalTime endTime) {
        if (startTime.plusHours(MIN_USE_HOUR).isAfter(endTime)) {
            throw new IllegalArgumentException(INVALID_VENUE_USE_TIME);
        }
    }

    public boolean isNotWithinTimeRange(LocalTime performanceStartTime, LocalTime performanceEndTime) {
        return performanceStartTime.isBefore(startTime) || performanceEndTime.isAfter(endTime);
    }
}
