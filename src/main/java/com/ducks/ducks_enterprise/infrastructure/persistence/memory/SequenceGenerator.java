package com.ducks.ducks_enterprise.infrastructure.persistence.memory;


import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SequenceGenerator {

    private static Long START_VALUE = 0L;
    private static Map<String, Long> sequenceMap = new HashMap<>();

    public static Long getSequenceNumber(String sequenceName) {
        if (StringUtils.hasText(sequenceName)) {
            sequenceMap.putIfAbsent(sequenceName, START_VALUE);
            return sequenceMap.computeIfPresent(sequenceName, (k, v) -> ++v);
        } else {
            throw new IllegalArgumentException("Sequence name cannot be null");
        }
    }
}
