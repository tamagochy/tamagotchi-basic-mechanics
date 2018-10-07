package ru.tamagotchi.basicmechanics.exception;

import lombok.Getter;

/**
 * Created by makar
 * 07.10.2018 12:07
 */
@Getter
public class IndexFullException extends RuntimeException {
    private String indexName;

    public IndexFullException(String indexName) {
        this.indexName = indexName;
    }
}
