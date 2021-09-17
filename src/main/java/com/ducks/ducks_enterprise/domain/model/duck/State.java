package com.ducks.ducks_enterprise.domain.model.duck;

import com.ducks.ducks_enterprise.domain.shared.ValueObject;

import java.util.*;

public enum State implements ValueObject<State> {
    SWIMMING,
    WALKING,
    RUNNING,
    FLYING,
    STANDING,
    EATING,
    SLEEPING;

    private static final Map<State, List<State>> STATES_CHANGES_MAP = new HashMap<>();

    static {
        STATES_CHANGES_MAP.put(SWIMMING, List.of(STANDING, WALKING, FLYING));
        STATES_CHANGES_MAP.put(WALKING, List.of(SWIMMING, RUNNING, STANDING));
        STATES_CHANGES_MAP.put(RUNNING, List.of(FLYING, WALKING));
        STATES_CHANGES_MAP.put(FLYING, List.of(SWIMMING, RUNNING));
        STATES_CHANGES_MAP.put(STANDING, List.of(SWIMMING, WALKING, SLEEPING, EATING));
        STATES_CHANGES_MAP.put(EATING, List.of(STANDING));
        STATES_CHANGES_MAP.put(SLEEPING, List.of(STANDING));
    }

    public static void updateStatesChangesMap(Map<State, List<State>> newMap) {
        STATES_CHANGES_MAP.clear();
        STATES_CHANGES_MAP.putAll(newMap);
    }

    public static void updateStatesChangesMap(State stateToUpdate, List<State> newValidStates) {
        STATES_CHANGES_MAP.put(stateToUpdate, newValidStates);
    }

    public static Duck changeState(Duck duck, State changeTo) {
        var currentState = Optional.ofNullable(duck).map(Duck::getState);
        if (currentState.isPresent()) {
            boolean isCorrectState = Optional.ofNullable(STATES_CHANGES_MAP.get(currentState.get()))
                    .map(states -> states.contains(changeTo))
                    .orElseThrow(IllegalArgumentException::new);
            duck.setState(isCorrectState ? changeTo : currentState.get());
        } else if (duck != null) {
            duck.setState(changeTo);
        }
        return duck;
    }
}
