package com.pokemon.core;

public abstract class EvolutionStrategy {
    private final String pokemonName;

    public EvolutionStrategy(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public abstract boolean canEvolve(Pokemon pokemon);
    public abstract void evolve(Pokemon pokemon);
}

