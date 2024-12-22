package com.pokemon.battle;

import java.util.ArrayList;
import java.util.List;

import com.pokemon.core.Move;
import com.pokemon.core.Pokemon;
import com.pokemon.utils.TypeEffectivenessMatrix;

class Battle {
    private List<BattleObserver> observers;

    public Battle() {
        observers = new ArrayList<>();
    }

    public void addObserver(BattleObserver observer) {
        observers.add(observer);
    }
    
    public void notifyObservers(String event) {
        for (BattleObserver observer : observers) {
            observer.update(event);
        }
    }
    
    // funzione per gestire l'attacco tra due pokemon, 
    public Boolean attack(Pokemon attacker, Pokemon defender, Move move) {
        notifyObservers(attacker.getName() + " attacca " + defender.getName() + " usando " + move.getName());
        double danno_totale = move.getPower(); // Base power of the move
        double effectiveness = 1.0;

        for (String tipoPokemonDifensore : defender.getType()) {
            effectiveness *= TypeEffectivenessMatrix.getEffectiveness(move.getType(), tipoPokemonDifensore);
        }

        danno_totale *= effectiveness;

        defender.getDamaged((int) danno_totale);
        if (defender.isFainted()) {
            notifyObservers(defender.getName() + " sconfitto!");
            return false;
        }
        return true;
    }
    

    
    
}
