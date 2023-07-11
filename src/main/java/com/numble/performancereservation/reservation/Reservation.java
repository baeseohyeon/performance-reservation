package com.numble.performancereservation.reservation;

import com.numble.performancereservation.base.BaseEntity;
import com.numble.performancereservation.payment.Payment;
import com.numble.performancereservation.performance.Performance;
import com.numble.performancereservation.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Reservation extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @JoinColumn(name = "performance_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Performance performance;
    @JoinColumn(name = "payment_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Payment payment;

    public Reservation(User user, Performance performance, Payment payment) {
        this.user = user;
        this.performance = performance;
        this.payment = payment;
    }
}
