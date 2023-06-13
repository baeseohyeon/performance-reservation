package com.numble.performancereservation.performance;

import com.numble.performancereservation.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Seat extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @Enumerated(EnumType.STRING)
    private SeatType type;
    private boolean isReserved;
    @JoinColumn(name = "venue_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Venue venue;
    @JoinColumn(name = "reservation_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Reservation reservation;

    @Builder
    public Seat(String number, SeatType type, Venue venue, Reservation reservation) {
        this.number = number;
        this.type = type;
        this.venue = venue;
        this.reservation = reservation;
    }
}
