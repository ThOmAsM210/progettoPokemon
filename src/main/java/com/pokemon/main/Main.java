package com.pokemon.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonSyntaxException;
import com.pokemon.battle.BattleManager;
import com.pokemon.core.Allenatore;
import com.pokemon.core.Pokemon;
import com.pokemon.core.PokemonPool;
import com.pokemon.data.DataLoader;
import com.pokemon.managers.EvolutionsManager;
import com.pokemon.managers.MovesManager;

public class Main {

    public static void main(String[] args) {
        // inizializzo managers (implementano Observer)
        MovesManager movesManager = MovesManager.getInstance();
        EvolutionsManager evolutionsManager = EvolutionsManager.getInstance();


        // inizializzo i il pool di pokemon, ma anche moves e evolutions
        PokemonPool pool = new PokemonPool(initializePokemon(movesManager, evolutionsManager));
        
        // inizializzo i due allenatori che si sfideranno
        Allenatore allenatore1 = new Allenatore("IOOOOO");
        Allenatore allenatore2 = new Allenatore("NEMICOOO");


        //creo i pokedex inizziali
        Scanner scanner = new Scanner(System.in);
        
        for (int i = 0; i < 8; i++) {
            System.out.println(pool.getAllPokemonNamesAndIndx());
            System.out.println("Seleziona un pokemon da aggiungere (0, " + (pool.getPoolSize()-1) + ")");
            int indx = scanner.nextInt();
            scanner.nextLine();
            allenatore1.addPokemon(pool.getPokemonInstanceByIndex(indx));
        }   
        
        for (int i = 0; i < 8; i++) {
            System.out.println(pool.getAllPokemonNamesAndIndx());
            System.out.println("Seleziona un pokemon da aggiungere (0, " + (pool.getPoolSize()-1) + ")");
            int indx = scanner.nextInt();
            scanner.nextLine();
            allenatore2.addPokemon(pool.getPokemonInstanceByIndex(indx));
        }
        

        // in battaglia prima faccio selezionare il team, poi mando i pokemon in battaglia, infine tutto ritorna al pokedex(resettando i danni)

        //gestisco la battaglia tra allenatore1 e 2
        BattleManager battleManager = new BattleManager(allenatore1, allenatore2);
        
        //avvio la battaglia(dall'inizio alla fine)
        battleManager.startBattle();


    }


    private static ArrayList<Pokemon> initializePokemon(MovesManager movesManager, EvolutionsManager evolutionsManager) {
        try {
            movesManager.setMoves(DataLoader.loadMovesData("src/main/resources/moves.json"));
            evolutionsManager.setStrategiesToEvolve(DataLoader.loadLevelEvolutionsData("src/main/resources/evolutions.json"));
            return DataLoader.loadPokemonData("src/main/resources/pokemon_data.json");
        } catch (IOException | JsonSyntaxException e) {
            System.out.println("Errore: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    
}
