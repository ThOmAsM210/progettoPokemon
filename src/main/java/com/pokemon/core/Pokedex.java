package com.pokemon.core;

import java.util.ArrayList;
import java.util.HashMap;


// l'insieme di pokemon che un allenatore possiede
public class Pokedex {
    ArrayList<Pokemon> pokemonAllenatore;
    HashMap<String, Boolean> pokemonConosciuti;

    public Pokedex() {
        pokemonAllenatore = new ArrayList<>();
        pokemonConosciuti = new HashMap<>();
    }

    public void addPokemon(Pokemon p) {
        pokemonAllenatore.add(p);
        pokemonConosciuti.put(p.getName(), true);
    }

    // ! non sto mettendo pokemonConosciuti a false, perchè l'allenatore l'ha vinto anche se ora non lo possiede più
    public void removePokemon(Pokemon p) {
        pokemonAllenatore.remove(p);
    }


    // restituisco il pokemon, ma lo tolgo dal pokedex perchè non sarà più disponibile (dovrà essere riaggiunto al team)
    public Pokemon borrowPokemon(int indx) {
        Pokemon p = pokemonAllenatore.get(indx);
        pokemonAllenatore.remove(indx);
        return p;
    }

    public String getPokemonConosciuti() {
        String s = "";
        for (String key : pokemonConosciuti.keySet()) {
            if(pokemonConosciuti.get(key)){
                s = s + key + '\n';
            }
        }
        return s;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < pokemonAllenatore.size(); i++) {
            s = s + i + ". " + pokemonAllenatore.get(i).getName() + '\n';
        }
        return s;
    }

    public int getSize() {
        return pokemonAllenatore.size();
    }   
}
