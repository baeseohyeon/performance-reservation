package com.numble.performancereservation.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Authority {

    @Id
    @Enumerated(EnumType.STRING)
    private Role role;

    public Authority(Role role) {
        this.role = role;
    }
}
