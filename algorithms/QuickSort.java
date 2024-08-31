package algorithms;

import java.util.List;
import java.util.Collections;
import model.Piece;

public class QuickSort {

    public static void quickSort(List<Piece> pieces, int low, int high, Piece[][] tablero, int delay, char color) {
        if (low < high) {
            int pi = partition(pieces, low, high);

            // Imprimir el tablero después de la partición
            Algorithms.reconstruirTablero(tablero, pieces);
            Algorithms.imprimirTableroConRetraso(tablero, delay, color);

            quickSort(pieces, low, pi - 1, tablero, delay, color);
            quickSort(pieces, pi + 1, high, tablero, delay, color);
        }
    }

    private static int partition(List<Piece> pieces, int low, int high) {
        Piece pivot = pieces.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (Algorithms.compare(pieces.get(j), pivot) <= 0) {
                i++;
                Collections.swap(pieces, i, j);
            }
        }
        Collections.swap(pieces, i + 1, high);
        return i + 1;
    }

}
