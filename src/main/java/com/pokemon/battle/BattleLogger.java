package com.pokemon.battle;


// uso per registrare gli eventi della battaglia
class BattleLogger implements BattleObserver {
    public void update(String event) {
        System.out.println("LOG: " + event);
    }
}