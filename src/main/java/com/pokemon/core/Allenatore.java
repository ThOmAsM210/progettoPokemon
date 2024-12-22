package com.pokemon.core;

// classe allenatore, quindi profilo giocatore
// ogni allenatore ha i suoi pokemon e un team che usa in battaglia
public class Allenatore {
    private final String name;
    private final Pokedex pokedex;
    private final Team team;

    // costruttore per creare un allenatore
    public Allenatore(String n) {
        this.name = n;
        this.pokedex = new Pokedex();
        this.team = new Team();
    }

    public String getName() {
        return name;
    }

    // metodi per getire il pokedex
    public String getPokemonConosciuti() {
        return pokedex.getPokemonConosciuti();
    }

    public String getPokedex() {
        return pokedex.toString();
    }

    public void addPokemon(Pokemon p) {
        pokedex.addPokemon(p);
    }

    public void removePokemon(Pokemon p) {
        pokedex.removePokemon(p);
    }

    public Pokemon borrowPokemon(int indx) {
        return pokedex.borrowPokemon(indx);
    }

    public String pokedexToString() {
        return pokedex.toString();
    }

    public int getPokedexSize() {
        return pokedex.getSize();
    }

    // metodi per gestire il team
    public void addPokemonToTeam(Pokemon p) {
        team.addPokemon(p);
    }

    public void removePokemonFromTeam(Pokemon p) {
        team.removePokemon(p);
    }

    public Pokemon borrowPokemonFromTeam(int indx) {
        return team.borrowPokemon(indx);
    }

    public String teamToString() {
        return team.toString();
    }

    public int getTeamSize() {
        return team.getSize();
    }

    @Override
    public String toString() {
        return name;
    }
}
