package com.pokemon.managers;

import java.util.HashMap;
import java.util.Map;

import com.pokemon.core.LevelEvolutionStrategy;
import com.pokemon.core.Observer;
import com.pokemon.core.Pokemon;


public class EvolutionsManager implements Observer{
    private Map<String, Map<String, LevelEvolutionStrategy>> strategiesToEvolve;
    private static EvolutionsManager instance;

    private EvolutionsManager() {
        strategiesToEvolve = new HashMap<>();
    }

    public static EvolutionsManager getInstance() {
        if(instance == null) {
            instance = new EvolutionsManager();
        }
        return instance;
    }

    public void setStrategiesToEvolve(Map<String, Map<String, LevelEvolutionStrategy>> strategiesToEvolve) {
        this.strategiesToEvolve = strategiesToEvolve;
    }

    private void tryEvolve(Pokemon pokemon) {
        if(strategiesToEvolve.containsKey(pokemon.getName())) {
            if(strategiesToEvolve.get(pokemon.getName()).containsKey((String.valueOf(pokemon.getLevel())))) {
                    strategiesToEvolve.get(pokemon.getName()).get(String.valueOf(pokemon.getLevel())).evolve(pokemon);
            }
        }
    }

    @Override
    public void update(Pokemon pokemon) {
        System.out.println("EvolutionsManager is updating " + pokemon.getName());
        tryEvolve(pokemon);
    }
}
