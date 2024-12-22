package com.pokemon.managers;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.pokemon.core.Move;
import com.pokemon.core.Observer;
import com.pokemon.core.Pokemon;


public class MovesManager implements Observer {
    private Map<String, Map<String, Move>> moves;
    private static MovesManager instance;

    private MovesManager() {
        moves = new HashMap<>();
    }

    public static MovesManager getInstance() {
        if(instance == null) {
            instance = new MovesManager();
        }
        return instance;
    }

    public void setMoves(Map<String, Map<String, Move>> moves) {
        this.moves = moves;
    }

    private void handlePossibleNewMove(Pokemon pokemon) {
        if(moves.containsKey(pokemon.getName())) {
            if(moves.get(pokemon.getName()).containsKey((String.valueOf(pokemon.getLevel())))) {
                if(pokemon.getMoves().size() < 4) {
                    pokemon.addMove(moves.get(pokemon.getName()).get(String.valueOf(pokemon.getLevel())));
                    System.out.println(pokemon.getName() + " ha imparato " + moves.get(pokemon.getName()).get(String.valueOf(pokemon.getLevel())).getName());
                }
                else {
                    System.out.println(pokemon.getName() + " ha provato ad imparare " + moves.get(pokemon.getName()).get(String.valueOf(pokemon.getLevel())).getName() + " ma non può imparare più di 4 mosse");
                    for(int i = 0; i<pokemon.getMoves().size(); i++) {
                        System.out.println(i + ". " + pokemon.getMoves().get(i).getName());
                    }
                    System.out.println("Vuoi sostituirne una? (y/n)");
                    Scanner scanner = new Scanner(System.in);
                    String answer = scanner.nextLine();

                    if(answer.equals("y")) {
                        System.out.println("Quale vuoi sostituire? (0-3)");
                        int index = scanner.nextInt();
                        pokemon.removeMove(index);
                        pokemon.addMove(moves.get(pokemon.getName()).get(String.valueOf(pokemon.getLevel())));
                        System.out.println(pokemon.getName() + " ha imparato " + moves.get(pokemon.getName()).get(String.valueOf(pokemon.getLevel())).getName());
                    }
                    else {
                        System.out.println(pokemon.getName() + " non ha imparato " + moves.get(pokemon.getName()).get(String.valueOf(pokemon.getLevel())).getName());
                    }
                }
            }
        }
    }

    @Override
    public void update(Pokemon pokemon) {
        System.out.println("Aggiorno " + pokemon.getName());
        handlePossibleNewMove(pokemon);
    }
    
}
