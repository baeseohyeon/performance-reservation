package com.numble.performancereservation.performance;

import com.numble.performancereservation.base.BaseEntity;
import com.numble.performancereservation.user.User;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
public class Performance extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    @Embedded
    private PerformanceDateTime dateTime;
    @Embedded
    private PerformancePrice price;
    @JoinColumn(name = "venue_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Venue venue;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Performance(User user, Venue venue, String name, int capacity,
        PerformanceDateTime dateTime, PerformancePrice price) {
        this.user = user;
        this.venue = venue;
        this.name = name;
        this.capacity = capacity;
        this.dateTime = dateTime;
        this.price = price;
    }
}
