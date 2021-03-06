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

    @Column(nullable = false, length = 20, unique = true)
    @Enumerated(EnumType.STRING)
    private ActionCode code;

    @Column(nullable = false, length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "MAIN_INDICATOR", nullable = false)
    private Indicator mainIndicator;

    @Column(name = "VALUE_1", nullable = false)
    private Integer value1;

    @ManyToOne
    @JoinColumn(name = "ADDITIONAL_INDICATOR")
    private Indicator additionalIndicator;

    @Column(name = "VALUE_2")
    private Integer value2;

    @Column(name = "DISEASE_CODE", length = 20)
    @Enumerated(EnumType.STRING)
    private DiseaseCode diseaseCode;
}
