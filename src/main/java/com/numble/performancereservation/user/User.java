package com.numble.performancereservation.user;

import com.numble.performancereservation.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    private String businessLicense;
    @Enumerated(EnumType.STRING)
    private ProducerType type;
    @ManyToMany
    @JoinTable(
        name = "user_authority",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role"))
    private Set<Authority> authorities;

    @Builder
    public User(String email, String username, String password, Set<Authority> authorities,
        String businessLicense, ProducerType type) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.businessLicense = businessLicense;
        this.type = type;
    }
}
