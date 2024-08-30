package algorithms;

import model.Piece;
import model.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertionSort implements SortAlgorithm {

    private Map<String, Integer> customOrder;

    public InsertionSort() {
        customOrder = new HashMap<>();
        
        // Definir el orden para los números
        int[] numOrder = {0, 3, 7, 5, 2, 1, 6, 8, 4, 9, 10, 11, 12, 13, 14, 15, 16};
        for (int i = 0; i < numOrder.length; i++) {
            customOrder.put(String.valueOf(numOrder[i]), i);
        }

        // Definir el orden para las letras
        char[] charOrder = {'c', 'g', 'e', 'b', 'a', 'f', 'h', 'd', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p'};
        for (int i = 0; i < charOrder.length; i++) {
            customOrder.put(String.valueOf(charOrder[i]), i + numOrder.length);
        }
    }


    @Override
    public void sort(Piece[][] tablero, int delay, char color) {
        int rows = tablero.length;
        int cols = tablero[0].length;

        // Convertir la matriz en un array unidimensional para facilitar el ordenamiento
        List<Piece> allPieces = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (tablero[i][j] != null) {
                    allPieces.add(tablero[i][j]);
                }
            }
        }

        // Aplicar el algoritmo de inserción al array unidimensional
        for (int i = 1; i < allPieces.size(); i++) {
            Piece key = allPieces.get(i);
            int j = i - 1;

            while (j >= 0 && compare(allPieces.get(j), key) > 0) {
                allPieces.set(j + 1, allPieces.get(j));
                j--;
            }
            allPieces.set(j + 1, key);

            // Reconstruir la matriz después de cada paso
            reconstruirTablero(tablero, allPieces);
            imprimirTableroConRetraso(tablero, delay, color);
        }
    }

    private void reconstruirTablero(Piece[][] tablero, List<Piece> allPieces) {
        int index = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (index < allPieces.size()) {
                    tablero[i][j] = allPieces.get(index);
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

        if (orderA == null || orderB == null) {
            throw new IllegalArgumentException("La pieza " + a.name + " o " + b.name + " no está definida en el orden personalizado.");
        }

        return orderA - orderB;
    }
}
