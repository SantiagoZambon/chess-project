package utils;

import java.util.Arrays;
import java.util.List;

public class ParameterValidator{

    // * Validacion algoritmo
    public static boolean validateAlgorithms(String sortAlgorithm) {
        List<String> validAlgorithms = Arrays.asList("i", "q", "b");

        return validAlgorithms.contains(sortAlgorithm.toLowerCase());
    }

    // * Validacion del tipo
    public static boolean validateType(char listType) {
        List<Character> validTypes = Arrays.asList('n', 'c');

        return validTypes.contains(listType);
    }

    // * Validacion del color
    public static boolean validateColors(char color) {
        List<Character> validColors = Arrays.asList('b', 'w');

        return validColors.contains(color);
    }

    // * Validacion de rango
    public static boolean validateRange(int pieceCount) {
        return pieceCount > 0 && pieceCount <= 16;
    }
}
