package algorithms;

import java.util.List;
import model.Piece;
import java.util.Collections;

public class QuickSort implements SortAlgorithm {

    @Override
    public void sort(List<Piece> pieces) {
        quickSort(pieces, 0, pieces.size() - 1);
    }

    private void quickSort(List<Piece> pieces, int low, int high) {
        if (low < high) {
            int pi = partition(pieces, low, high);
            quickSort(pieces, low, pi - 1);
            quickSort(pieces, pi + 1, high);
        }
    }

    private int partition(List<Piece> pieces, int low, int high) {
        int pivot = Integer.parseInt(pieces.get(high).name);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (Integer.parseInt(pieces.get(j).name) < pivot) {
                i++;
                Collections.swap(pieces, i, j);
            }
        }
        Collections.swap(pieces, i + 1, high);
        return i + 1;
    }
}
