package com.ducks.ducks_enterprise.domain.model.duck;

import com.ducks.ducks_enterprise.domain.shared.Entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "set")
public class Duck implements Entity<Duck> {

    public static final String SEQUENCE_NAME = "duck_sequence";

    private long duckId;
    private int age;
    private Color color;
    private String name;
    private double weight;
    private double wingsLength;
    private State state;

}
