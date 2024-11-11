package huyvu.leathershop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@MappedSuperclass // Indicates that this is a base class for JPA entities
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Auto-generate ID
    @Column(updatable = false)
    private String id;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // Constructors
    public BaseModel() {
        // Initialize createdAt when the entity is first created
        this.createdAt = LocalDateTime.now();
    }


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
