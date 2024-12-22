package com.pokemon.core;

public class Move {
    private final String name;
    private final int power;
    private final String type;

    public Move(String n, int p,String t) {
        this.name = n;
        this.power = p;
        this.type = t;
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return name + " - " + type + " - " + power;
    }
}
