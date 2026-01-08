package ru.practicum.shareit.request.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import ru.practicum.shareit.user.model.User;

@Entity
@Table(name = "requests")
@Data
public class ItemRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "requestor_id", nullable = false)
    private User requestor;

    @Column(nullable = false)
    private LocalDateTime created;

    @PrePersist
    private void onCreate() {
        if (created == null) {
            created = LocalDateTime.now();
        }
    }
}