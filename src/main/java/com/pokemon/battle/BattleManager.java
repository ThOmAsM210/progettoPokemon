package com.pokemon.battle;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.pokemon.core.Allenatore;
import com.pokemon.core.Move;
import com.pokemon.core.Pokemon;

public class BattleManager {
    Allenatore allenatore1;
    Allenatore allenatore2;

    public BattleManager(Allenatore allenatore1, Allenatore allenatore2) {
        this.allenatore1 = allenatore1;
        this.allenatore2 = allenatore2;
    }

    public void startBattle() {
        Battle battle = new Battle();
        BattleLogger logger = new BattleLogger();
        battle.addObserver(logger);

        selectTeam(this.allenatore1);
        selectTeam(this.allenatore2);
        
        while(this.allenatore1.getTeamSize() > 0 && this.allenatore2.getTeamSize() > 0) {
        	scontro(battle);
        }
        
        if(this.allenatore1.getTeamSize() == 0 ) {
        	logger.update("Allenatore: " + this.allenatore2.getName() + " vince la battaglia");
        	//ritorno i pokemon al pokedex --> svuoto il team
        	for(int i = 0; i<this.allenatore2.getTeamSize(); i++) {
        		this.allenatore2.borrowPokemonFromTeam(i).resetBattleHp();
        		this.allenatore2.addPokemon(this.allenatore2.borrowPokemonFromTeam(i));
        	}
        }
        else {
        	logger.update("Allenatore: " + this.allenatore1.getName() + " vince la battaglia");
        	//ritorno i pokemon al pokedex --> svuoto il team
        	for(int i = 0; i<this.allenatore1.getTeamSize(); i++) {
        		this.allenatore1.borrowPokemonFromTeam(i).resetBattleHp();
        		this.allenatore1.addPokemon(this.allenatore1.borrowPokemonFromTeam(i));
        	}
        }
        
    }
    
    //comincia il giocatore 1
    private void scontro(Battle b) {
    	Pokemon p1 = chooseActivePokemon(this.allenatore1);
    	Pokemon p2 = chooseActivePokemon(this.allenatore2);
    	
    	
    	while(true) {
    		Move m1 = selectActiveMove(p1);
    		if(!b.attack(p1, p2, m1)) break;
    		Move m2 = selectActiveMove(p2);
    		if(!b.attack(p2, p1, m2)) break;
    	}
    	
    	if(p1.isFainted()) {
    		p1.resetBattleHp();
    		this.allenatore1.addPokemon(p1);
    	}
    	else {
    		p2.resetBattleHp();
    		this.allenatore2.addPokemon(p2);
    	}
    }

    private void selectTeam(Allenatore allenatore) {
    	
    	Scanner scanner = new Scanner(System.in); 
	    System.out.println("Allenatore: " + allenatore.getName());
	    System.out.println("Selezionare i 6 pokemon da utilizzare in battaglia");

	    int pokemonIndex = -1;

	    while (allenatore.getTeamSize() < 6) {
	        try {
	            System.out.println(allenatore.pokedexToString());
	            System.out.println("Inserire l'indice del pokemon da aggiungere al team (0," + (allenatore.getPokedexSize() - 1) + ")");
	            pokemonIndex = scanner.nextInt(); // Legge l'indice
	            scanner.nextLine();

	            // Verifica che l'indice sia valido
	            if (pokemonIndex < 0 || pokemonIndex >= allenatore.getPokedexSize()) {
	                System.out.println("Indice non valido. Riprovare.");
	                continue;
	            }

	            // Aggiunge il Pokémon al team
	            allenatore.addPokemonToTeam(allenatore.borrowPokemon(pokemonIndex));
	        } catch (NoSuchElementException e) {
	            System.out.println("Errore: Inserire un numero valido.");
	        }
	    }
    	

    }

    private Pokemon chooseActivePokemon(Allenatore allenatore) {
        System.out.println("Allenatore " + allenatore.getName() + ", scegli il tuo Pokémon attivo:");
        System.out.println(allenatore.teamToString());
        int pokemonIndex = Integer.parseInt(System.console().readLine());
        return allenatore.borrowPokemonFromTeam(pokemonIndex);
    }
    
    // seleziono la mossa da usare
    private Move selectActiveMove(Pokemon p) {
        Scanner scanner = new Scanner(System.in);

        if (p.getMoves().isEmpty()) {
            System.out.println(p.getName() + " non ha mosse disponibili!");
            return null;
        }

        System.out.println("Scegli una mossa per " + p.getName() + ":");
        for (int i = 0; i < p.getMoves().size(); i++) {
            System.out.println(i + ": " + p.getMoves().get(i).getName());
        }

        int selectedIndex = -1;
        while (selectedIndex < 0 || selectedIndex >= p.getMoves().size()) {
            System.out.print("Inserisci il numero della mossa: ");
            selectedIndex = scanner.nextInt();
            scanner.nextLine();

            if (selectedIndex < 0 || selectedIndex >= p.getMoves().size()) {
                System.out.println("Selezione non valida. Riprova.");
            }
        }
        

        return p.getMoves().get(selectedIndex);
    }
}