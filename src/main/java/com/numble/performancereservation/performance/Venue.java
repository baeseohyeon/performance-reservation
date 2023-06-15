package com.numble.performancereservation.performance;

import com.numble.performancereservation.base.BaseEntity;
import com.numble.performancereservation.user.User;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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
public class Venue extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    @Enumerated
    private VenueType type;
    @Embedded
    private PossibleTime possibleTime;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Builder
    public Venue(User user, String name, int capacity, VenueType type, String possibleTime) {
        this.user = user;
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.possibleTime = new PossibleTime(possibleTime);
    }
}
