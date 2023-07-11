package com.numble.performancereservation.venue;

import static com.numble.performancereservation.exception.ExceptionMessage.PERFORMANCE_TIME_MUST_WITHIN_VENUE_USE_TIME;

import com.numble.performancereservation.base.BaseEntity;
import com.numble.performancereservation.user.User;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Venue extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private VenueType type;
    @Embedded
    private PossibleTime possibleTime;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Venue(User user, String name, int capacity, VenueType type, String possibleTime) {
        this.user = user;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.possibleTime = new PossibleTime(possibleTime);
    }

    public void validatePerformanceTimeWithinRange(LocalTime startTime, LocalTime endTime) {
        if(possibleTime.isNotWithinTimeRange(startTime, endTime)){
            throw new IllegalArgumentException(PERFORMANCE_TIME_MUST_WITHIN_VENUE_USE_TIME);
        }
    }
}
