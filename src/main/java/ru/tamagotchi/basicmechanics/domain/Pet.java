package ru.tamagotchi.basicmechanics.domain;

import lombok.AllArgsConstructor;
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
@NoArgsConstructor
@AllArgsConstructor
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

    @Column
    private LocalDateTime lastAccessTime;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetStatus status;
}
