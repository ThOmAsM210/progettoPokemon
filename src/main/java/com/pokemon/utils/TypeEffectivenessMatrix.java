package com.pokemon.utils;

public class TypeEffectivenessMatrix {
    private static final double[][] effectivenessMatrix = {
        // Normale, Fuoco, Acqua, Erba, Elettrico, Roccia, Volante
        { 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0 }, // Normale
        { 1.0, 0.5, 0.5, 2.0, 1.0, 2.0, 1.0 }, // Fuoco
        { 1.0, 2.0, 0.5, 0.5, 1.0, 2.0, 1.0 }, // Acqua
        { 1.0, 0.5, 2.0, 0.5, 1.0, 1.0, 0.5 }, // Erba
        { 1.0, 1.0, 1.0, 0.5, 0.5, 1.0, 2.0 }, // Elettrico
        { 1.0, 0.5, 1.0, 2.0, 1.0, 1.0, 0.5 }, // Roccia
        { 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0 }  // Volante
    };

    private static final String[] types = { "Normal", "Fire", "Water", "Grass", "Electric", "Rock", "Flying" };

    public static double getEffectiveness(String attackType, String targetType) {
        int attackIndex = getIndex(attackType);
        int targetIndex = getIndex(targetType);
        return effectivenessMatrix[attackIndex][targetIndex];
    }

    private static int getIndex(String type) {
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(type)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Tipo non valido: " + type);
    }
}
