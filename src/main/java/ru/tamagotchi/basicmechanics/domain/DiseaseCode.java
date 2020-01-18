package ru.tamagotchi.basicmechanics.domain;

import java.util.Random;

/**
 * Created by makar
 * 17.12.2018 22:48
 */
public enum DiseaseCode {
    /**
     * Простуда
     */
    cold,

    /**
     * Ангина
     */
    angina,

    /**
     * Воспаление хитрости
     */
    inflammationTricks,

    /**
     * Аллергия
     */
    allergy;

    public static DiseaseCode random() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
