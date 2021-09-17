package com.ducks.ducks_enterprise.interfaces.ducks;

import lombok.Builder;
import lombok.Getter;

@Builder(setterPrefix = "set")
@Getter
public class DuckDTO {
    private final long duckId;
    private final int age;
    private final String color;
    private final String name;
    private final double weight;
    private final double wingsLength;
    private final String state;
}
