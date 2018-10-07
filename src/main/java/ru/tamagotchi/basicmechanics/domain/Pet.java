package ru.tamagotchi.basicmechanics.domain;

import lombok.Data;

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
    public static final int INDEX_MAX_VALUE = 100;
    public static final int INDEX_INCREMENT_VALUE = 25;

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

    public void increaseHealth() {
        this.health += INDEX_INCREMENT_VALUE;
        if (this.health > INDEX_MAX_VALUE) {
            this.health = INDEX_MAX_VALUE;
        }
    }

    public void increaseHunger() {
        this.hunger += INDEX_INCREMENT_VALUE;
        if (this.hunger > INDEX_MAX_VALUE) {
            this.hunger = INDEX_MAX_VALUE;
        }
    }

    public void increaseRest() {
        this.rest += INDEX_INCREMENT_VALUE;
        if (this.rest > INDEX_MAX_VALUE) {
            this.rest = INDEX_MAX_VALUE;
        }
    }

    public void increaseMood() {
        this.mood += INDEX_INCREMENT_VALUE;
        if (this.mood > INDEX_MAX_VALUE) {
            this.mood = INDEX_MAX_VALUE;
        }
    }
}
