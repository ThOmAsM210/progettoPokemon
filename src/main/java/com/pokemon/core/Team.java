package com.pokemon.core;

import java.util.ArrayList;

// il team di Pokemon selezzionati da un allenatore
public class Team {

    private final ArrayList<Pokemon> team;

    public Team() {
        team = new ArrayList<>();
    }

    public void addPokemon(Pokemon p) {
        team.add(p);
    }

    public void removePokemon(Pokemon p) {
        team.remove(p);
    }

    public Pokemon borrowPokemon(int indx) {
        Pokemon p = team.get(indx);
        team.remove(indx);
        return p;
    }

    public int getSize() {
        return team.size();
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < team.size(); i++) {
            s = s + i + ". " + team.get(i).getName() + '\n';
        }
        return s;
    }   
}