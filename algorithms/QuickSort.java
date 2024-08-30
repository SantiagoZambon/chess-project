package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import model.Piece;
import model.Board;

public class QuickSort implements SortAlgorithm {

    private Map<String, Integer> customOrder;

    public QuickSort() {
        // Definir el orden personalizado para números y letras
        customOrder = new HashMap<>();

        // Orden para los números
        int[] numOrder = { 0, 3, 7, 5, 2, 1, 6, 8, 4, 9, 10, 11, 12, 13, 14, 15, 16 };
        for (int i = 0; i < numOrder.length; i++) {
            customOrder.put(String.valueOf(numOrder[i]), i);
        }

        // Orden para las letras
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

        // Ordenar la lista usando el método quickSort
        quickSort(pieces, 0, pieces.size() - 1, tablero, delay, color);

        // Asignar las piezas ordenadas de nuevo a la matriz
        reconstruirTablero(tablero, pieces);
    }

    private void quickSort(List<Piece> pieces, int low, int high, Piece[][] tablero, int delay, char color) {
        if (low < high) {
            int pi = partition(pieces, low, high);

            // Imprimir el tablero después de la partición
            reconstruirTablero(tablero, pieces);
            imprimirTableroConRetraso(tablero, delay, color);

            quickSort(pieces, low, pi - 1, tablero, delay, color);
            quickSort(pieces, pi + 1, high, tablero, delay, color);
        }
    }

    private int partition(List<Piece> pieces, int low, int high) {
        Piece pivot = pieces.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (compare(pieces.get(j), pivot) <= 0) {
                i++;
                Collections.swap(pieces, i, j);
            }
        }
        Collections.swap(pieces, i + 1, high);
        return i + 1;
    }

    private void reconstruirTablero(Piece[][] tablero, List<Piece> pieces) {
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

    private void imprimirTableroConRetraso(Piece[][] tablero, int delay, char color) {
        Board board = new Board(convertToList(tablero), color);
        board.imprimirTablero();

        // Retraso
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private List<Piece> convertToList(Piece[][] tablero) {
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

    private int compare(Piece a, Piece b) {
        Integer orderA = customOrder.get(a.name);
        Integer orderB = customOrder.get(b.name);

        // Manejar casos donde la clave no existe en el mapa
        if (orderA == null || orderB == null) {
            throw new IllegalArgumentException(
                    "La pieza " + a.name + " o " + b.name + " no está definida en el orden personalizado.");
        }

        return orderA - orderB;
    }
}
