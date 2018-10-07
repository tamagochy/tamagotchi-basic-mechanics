package ru.tamagotchi.basicmechanics.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by makar
 * 07.10.2018 20:56
 */
@Entity
@Table(name = "ACTIONS")
@Data
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ActionCode code;

    @Column(nullable = false, length = 100)
    private String description;

    @Column(nullable = false)
    private Integer value;
}
