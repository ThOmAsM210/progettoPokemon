package com.pokemon.core;

public class LevelEvolutionStrategy extends  EvolutionStrategy {
    private final int requiredLevel;
    private final String nextForm;
    private final int level;
    private final int attack;
    private final int defense;
    private final int speed;
    private final int hp;
    private final int exp;
    private final int expToNextLevel;

    public LevelEvolutionStrategy(String pokemonName, int requiredLevel, String nextForm, int level, int attack, int defense, int speed, int hp, int expToNextLevel, int exp) {
        super(pokemonName);
        this.requiredLevel = requiredLevel;
        this.nextForm = nextForm;
        this.level = level;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.hp = hp;
        this.exp = exp;
        this.expToNextLevel = expToNextLevel;
    }

    @Override
    public boolean canEvolve(Pokemon pokemon) {
        return pokemon.getLevel() >= this.requiredLevel;
    }

    @Override
    public void evolve(Pokemon pokemon) {
        System.out.println(pokemon.getName() + " is evolving to " + nextForm + "!");
        pokemon.setName(nextForm);
        pokemon.setLevel(level);
        pokemon.setAttack(attack);
        pokemon.setDefense(defense);
        pokemon.setSpeed(speed);
        pokemon.setHp(hp);
        pokemon.setExp(exp);
        pokemon.setExpToNextLevel(expToNextLevel);
    }
}
