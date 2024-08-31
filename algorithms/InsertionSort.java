package algorithms;

import model.Piece;
import java.util.List;

public class InsertionSort {

    public static void insertionSort(List<Piece> pieces, Piece[][] tablero, int delay, char color) {

        for (int i = 1; i < pieces.size(); i++) {
            Piece key = pieces.get(i);
            int j = i - 1;

            while (j >= 0 && Algorithms.compare(pieces.get(j), key) > 0) {
                pieces.set(j + 1, pieces.get(j));
                j--;
            }
            pieces.set(j + 1, key);

            // Reconstruir la matriz después de cada paso
            Algorithms.reconstruirTablero(tablero, pieces);
            Algorithms.imprimirTableroConRetraso(tablero, delay, color);
        }
    }
}
