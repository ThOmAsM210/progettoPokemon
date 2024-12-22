package com.pokemon.data;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.pokemon.core.LevelEvolutionStrategy;
import com.pokemon.core.Move;
import com.pokemon.core.Pokemon;

public class DataLoader {

    public static ArrayList<Pokemon> loadPokemonData(String filePath) throws IOException {
        Gson gson = new Gson();
        
        Type listType = new TypeToken<ArrayList<Pokemon>>() {}.getType();

        try (FileReader reader = new FileReader(filePath)) {
            // Deserialize JSON into ArrayList<Pokemon>
            ArrayList<Pokemon> pokemonList = gson.fromJson(reader, listType);
            return pokemonList;
        }
    }

    public static Map<String, Map<String, Move>> loadMovesData(String jsonFilePath) {
        Map<String, Map<String, Move>> pokemonMoves = new HashMap<>();
        try (Reader reader = new FileReader(jsonFilePath)) {
            // Parse JSON into a nested structure
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            // Iterate through Pokémon names
            for (String pokemonName : jsonObject.keySet()) {
                Map<String, Move> levelMoves = new HashMap<>();
                JsonObject levels = jsonObject.getAsJsonObject(pokemonName);

                // Iterate through levels and moves
                for (String level : levels.keySet()) {
                    JsonArray movesArray = levels.getAsJsonArray(level);
                    for (JsonElement moveElement : movesArray) {
                        JsonObject moveObject = moveElement.getAsJsonObject();
                        String name = moveObject.get("name").getAsString();
                        int power = moveObject.get("power").getAsInt();
                        String type = moveObject.get("type").getAsString();

                        // Create Move object
                        Move move = new Move(name, power, type);
                        levelMoves.put(level, move);
                    }
                }
                pokemonMoves.put(pokemonName, levelMoves);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + e.getMessage());   
        }

        return pokemonMoves;
    }

    public static Map<String, Map<String, LevelEvolutionStrategy>> loadLevelEvolutionsData(String jsonFilePath) {
        Map<String, Map<String, LevelEvolutionStrategy>> pokemonEvolutions = new HashMap<>();
        try (Reader reader = new FileReader(jsonFilePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

            for (String pokemonName : jsonObject.keySet()) {
                Map<String, LevelEvolutionStrategy> levelEvolutions = new HashMap<>();
                JsonObject levels = jsonObject.getAsJsonObject(pokemonName);

                for (String level : levels.keySet()) {
                    JsonArray evolutionsArray = levels.getAsJsonArray(level);
                    for (JsonElement evolutionElement : evolutionsArray) {
                        JsonObject evolutionObject = evolutionElement.getAsJsonObject();
                        String nextForm = evolutionObject.get("nextForm").getAsString();
                        int plevel = evolutionObject.get("level").getAsInt();
                        int attack = evolutionObject.get("attack").getAsInt();
                        int defense = evolutionObject.get("defense").getAsInt();
                        int speed = evolutionObject.get("speed").getAsInt();
                        int hp = evolutionObject.get("hp").getAsInt();
                        int exp = evolutionObject.get("exp").getAsInt();
                        int expToNextLevel = evolutionObject.get("expToNextLevel").getAsInt();

                        LevelEvolutionStrategy evolution = new LevelEvolutionStrategy(pokemonName, Integer.parseInt(level), nextForm, plevel, attack, defense, speed, hp, exp, expToNextLevel);
                        levelEvolutions.put(level, evolution);
                    }
                }
                pokemonEvolutions.put(pokemonName, levelEvolutions);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file: " + e.getMessage());   
        }

        return pokemonEvolutions;
    }
}
