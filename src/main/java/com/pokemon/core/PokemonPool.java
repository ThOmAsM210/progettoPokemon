package com.pokemon.core;

import java.util.ArrayList;


// pool generale con tutti i pokemon disponibili nel gioco
public class PokemonPool {
    private final ArrayList<Pokemon> tuttiPokemon;
    
        public PokemonPool(ArrayList<Pokemon> a) {
            this.tuttiPokemon = a;   
    }

    public String getAllPokemonNamesAndIndx() {
        String s = "";
        for(int i = 0; i<tuttiPokemon.size(); i++) {
            s = s + i + ". " + tuttiPokemon.get(i).getName() + '\n';
        }
        return s;
    }

    public Pokemon getPokemonInstanceByIndex(int indx) {
        return clone(tuttiPokemon.get(indx));
    }

    public int getPoolSize() {
        return tuttiPokemon.size();
    }


    //funzione per dare un pokemon ad un allenatore
    private Pokemon clone(Pokemon p) {
        return new Pokemon(p);
    }
    
}
