package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import model.Piece;
import model.Board;

public class BubbleSort implements SortAlgorithm {

    private Map<String, Integer> customOrder;

    public BubbleSort() {
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

        // Ordenar la lista usando el método bubbleSort
        bubbleSort(pieces, tablero, delay, color);

        // Asignar las piezas ordenadas de nuevo a la matriz
        reconstruirTablero(tablero, pieces);
    }

    private void bubbleSort(List<Piece> pieces, Piece[][] tablero, int delay, char color) {
        int n = pieces.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (compare(pieces.get(j), pieces.get(j + 1)) > 0) {
                    Collections.swap(pieces, j, j + 1);

                    // Imprimir el tablero después de cada intercambio
                    reconstruirTablero(tablero, pieces);
                    imprimirTableroConRetraso(tablero, delay, color);
                }
            }
        }
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
