package utils;

import java.util.Arrays;
import java.util.List;

public class ParameterValidator {

    public static boolean validateParameters(String sortAlgorithm, char listType, char color, int pieceCount) {
        List<String> validAlgorithms = Arrays.asList("i", "q");
        List<Character> validTypes = Arrays.asList('n', 'c');
        List<Character> validColors = Arrays.asList('b', 'w');

        return validAlgorithms.contains(sortAlgorithm.toLowerCase()) &&
                validTypes.contains(listType) &&
                validColors.contains(color) &&
                pieceCount > 0 && pieceCount <= 16;
    }
}
