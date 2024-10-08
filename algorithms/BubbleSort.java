package algorithms;

import java.util.List;
import java.util.Collections;
import model.Piece;

public class BubbleSort {

    //! Bubble Sort
    public static void bubbleSort(List<Piece> pieces, Piece[][] tablero, int delay, char color) {
        int n = pieces.size();
        for (int i = 0; i < n - 1; i++) { 
            for (int j = 0; j < n - i - 1; j++) { 
                // Comparar elementos y cambiar si están en el orden incorrecto
                if (Algorithms.compare(pieces.get(j), pieces.get(j + 1)) > 0) {
                    Collections.swap(pieces, j, j + 1);

                    // Reset tablero
                    Algorithms.reconstruirTablero(tablero, pieces);
                    Algorithms.imprimirTableroConRetraso(tablero, delay, color);
                }
            }
        }
    }
}
