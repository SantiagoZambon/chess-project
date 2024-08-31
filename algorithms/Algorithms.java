package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Piece;
import model.Board;

public class Algorithms implements SortAlgorithm {

    // Mapa para almacenar el orden personalizado de números y letras
    private static Map<String, Integer> customOrder;

    String algoritmo;

    public Algorithms(String algoritmo) {
        this.algoritmo = algoritmo;

        // * Inicializar el mapa con el orden personalizado
        customOrder = new HashMap<>();

        // Definir el orden de los números
        int[] numOrder = { 0, 3, 7, 5, 2, 1, 6, 8, 4, 9, 10, 11, 12, 13, 14, 15, 16 };
        for (int i = 0; i < numOrder.length; i++) {
            customOrder.put(String.valueOf(numOrder[i]), i);
        }

        // Definir el orden de las letras
        char[] charOrder = { 'c', 'g', 'e', 'b', 'a', 'f', 'h', 'd', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p' };
        for (int i = 0; i < charOrder.length; i++) {
            customOrder.put(String.valueOf(charOrder[i]), i + numOrder.length);
        }
    }

    @Override
    public void sort(Piece[][] tablero, int delay, char color) {
        List<Piece> pieces = new ArrayList<>();

        // * Recoger todas las piezas del tablero en una lista
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j] != null) {
                    pieces.add(tablero[i][j]);
                }
            }
        }

        // * Seleccionar el algoritmo de ordenamiento según la opción del usuario
        switch (algoritmo.toLowerCase()) {
            case "i":
                InsertionSort.insertionSort(pieces, tablero, delay, color);
                break;
            case "q":
                QuickSort.quickSort(pieces, 0, pieces.size() - 1, tablero, delay, color);
                break;
            case "b":
                BubbleSort.bubbleSort(pieces, tablero, delay, color);
                break;
        }


        reconstruirTablero(tablero, pieces);
    }

    // * Coloca las piezas ordenadas de vuelta en el tablero
    public static void reconstruirTablero(Piece[][] tablero, List<Piece> pieces) {
        int index = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (index < pieces.size()) {
                    tablero[i][j] = pieces.get(index);
                    index++;
                } else {
                    tablero[i][j] = null;
                }
            }
        }
    }

    // * Imprime el tablero y pausa la ejecución según el delay dado
    public static void imprimirTableroConRetraso(Piece[][] tablero, int delay, char color) {
        Board board = new Board(convertToList(tablero), color);

        System.out.println("\n");
        board.imprimirTablero();

        // Pausa para simular un retraso
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // * Convierte el tablero 2D en una lista para facilitar su manipulación
    public static List<Piece> convertToList(Piece[][] tablero) {
        List<Piece> pieces = new ArrayList<>();
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] != null) {
                    pieces.add(tablero[i][j]);
                }
            }
        }
        return pieces;
    }

    // * Compara dos piezas según el orden personalizado
    public static int compare(Piece a, Piece b) {
        Integer orderA = customOrder.get(a.name);
        Integer orderB = customOrder.get(b.name);

        // Verifica que ambas piezas estén en el mapa
        if (orderA == null || orderB == null) {
            throw new IllegalArgumentException(
                    "La pieza " + a.name + " o " + b.name + " no está definida en el orden personalizado.");
        }

        return orderA - orderB; // Devuelve la diferencia entre las posiciones en el orden personalizado
    }
}
