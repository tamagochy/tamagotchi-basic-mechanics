package ru.tamagotchi.basicmechanics.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by makar
 * 02.10.2018 18:13
 */
@Entity
@Table(name = "PETS")
@Data
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer ownerId;

    @Column(nullable = false)
    private Integer health;

    @Column(nullable = false)
    private Integer hunger;

    @Column(nullable = false)
    private Integer rest;

    @Column(nullable = false)
    private Integer mood;

    @Column(nullable = false)
    private LocalDateTime createTime;

    @Column(nullable = false)
    private LocalDateTime lastAccessTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;

    public Pet(
            Integer ownerId,
            Integer health,
            Integer hunger,
            Integer rest,
            Integer mood,
            LocalDateTime createTime,
            LocalDateTime lastAccessTime,
            PetStatus status
    ) {
        this.ownerId = ownerId;
        this.health = health;
        this.hunger = hunger;
        this.rest = rest;
        this.mood = mood;
        this.createTime = createTime;
        this.lastAccessTime = lastAccessTime;
        this.status = status;
    }
}
