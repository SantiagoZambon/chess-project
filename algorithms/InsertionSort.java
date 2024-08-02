//! Algoritmo Insertion
package algorithms;

import java.util.List;
import model.Piece;

public class InsertionSort implements SortAlgorithm {

    @Override
    public void sort(List<Piece> pieces) {
        for (int i = 1; i < pieces.size(); i++) {
            Piece key = pieces.get(i);
            int j = i - 1;


            while (j >= 0 && pieces.get(j).compareTo(key) > 0) {
                pieces.set(j + 1, pieces.get(j));
                j--;
            }
            pieces.set(j + 1, key);
        }
    }
}
