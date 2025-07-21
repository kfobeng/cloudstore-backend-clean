package com.cloudstore.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    private boolean emailVerified = false;

    private String emailVerificationCode;
    private LocalDateTime emailVerificationExpiry;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(name = "storage_limit")
private Long storageLimit = 500_000_000L; // Default 500MB

@Column(name = "used_storage")
private Long usedStorage = 0L;

public boolean isOutOfStorage() {
    return usedStorage >= storageLimit;
}


    @PrePersist
    protected void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
} 