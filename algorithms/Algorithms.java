package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Piece;
import model.Board;

public class Algorithms implements SortAlgorithm {

    private static Map<String, Integer> customOrder;

    String algoritmo;

    public Algorithms(String algoritmo) {
        this.algoritmo = algoritmo;

        // Definir el orden personalizado para números y letras
        customOrder = new HashMap<>();

        // Orden números
        int[] numOrder = { 0, 3, 7, 5, 2, 1, 6, 8, 4, 9, 10, 11, 12, 13, 14, 15, 16 };
        for (int i = 0; i < numOrder.length; i++) {
            customOrder.put(String.valueOf(numOrder[i]), i);
        }

        // Orden letras
        char[] charOrder = { 'c', 'g', 'e', 'b', 'a', 'f', 'h', 'd', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p' };
        for (int i = 0; i < charOrder.length; i++) {
            customOrder.put(String.valueOf(charOrder[i]), i + numOrder.length);
        }
    }

    @Override
    public void sort(Piece[][] tablero, int delay, char color) {
        List<Piece> pieces = new ArrayList<>();

        // Recoger todas las piezas no nulas en una lista
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j] != null) {
                    pieces.add(tablero[i][j]);
                }
            }
        }

        // Seleccion de algoritmo de ordenamieto
        switch (algoritmo.toLowerCase()) {
            case "i":
                InsertionSort.insertionSort(pieces, tablero, delay, color);
            case "q":
                QuickSort.quickSort(pieces, 0, pieces.size() - 1, tablero, delay, color);
            case "b":
                BubbleSort.bubbleSort(pieces, tablero, delay, color);
        }

        reconstruirTablero(tablero, pieces);
    }

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

    public static void imprimirTableroConRetraso(Piece[][] tablero, int delay, char color) {
        Board board = new Board(convertToList(tablero), color);

        System.out.println("\n");
        board.imprimirTablero();

        // Delay
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

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

    public static int compare(Piece a, Piece b) {
        Integer orderA = customOrder.get(a.name);
        Integer orderB = customOrder.get(b.name);

        // casos donde la clave no existe en el mapa
        if (orderA == null || orderB == null) {
            throw new IllegalArgumentException(
                    "La pieza " + a.name + " o " + b.name + " no está definida en el orden personalizado.");
        }

        return orderA - orderB;
    }
}
