//! Algoritmo Bubble 
package algorithms;

import java.util.List;
import model.Piece;
import java.util.Collections;

public class BubbleSort implements SortAlgorithm {

    @Override
    public void sort(List<Piece> pieces) {
        bubbleSort(pieces);
    }

    private void bubbleSort(List<Piece> pieces) {
        int n = pieces.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (pieces.get(j).compareTo(pieces.get(j + 1)) > 0) {
                    Collections.swap(pieces, j, j + 1);
                }
            }
        }
    }
}
