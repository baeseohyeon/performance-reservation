package com.numble.performancereservation.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
@DiscriminatorValue("P")
public class Producer extends User {

    private String businessLicense;
    @Enumerated(EnumType.STRING)
    private ProducerType type;

    public Producer(String businessLicense, ProducerType type) {
        this.businessLicense = businessLicense;
        this.type = type;
    }

    @Builder
    public Producer(String email, String username, String password, String businessLicense,
        ProducerType type) {
        super(email, username, password);
        this.businessLicense = businessLicense;
        this.type = type;
    }
}
