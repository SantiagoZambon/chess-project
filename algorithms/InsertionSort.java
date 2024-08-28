package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Piece;

public class InsertionSort implements SortAlgorithm {

    private Map<String, Integer> customOrder;

    public InsertionSort() {
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
            customOrder.put(String.valueOf(charOrder[i]), i);
        }
    }

    @Override
    public void sort(List<Piece> pieces) {
        for (int i = 1; i < pieces.size(); i++) {
            Piece key = pieces.get(i);
            int j = i - 1;

            while (j >= 0 && compare(pieces.get(j), key) > 0) {
                pieces.set(j + 1, pieces.get(j));
                j--;
            }
            pieces.set(j + 1, key);
        }
    }

    @Override
    public void sort(Piece[][] tablero) {
        List<Piece> pieces = new ArrayList<>();

        // Recoger todas las piezas no nulas en una lista
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j] != null) {
                    pieces.add(tablero[i][j]);
                }
            }
        }

        // Ordenar la lista usando el método sort(List<Piece> pieces)
        sort(pieces);

        // Asignar las piezas ordenadas de nuevo a la matriz
        int index = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (index < pieces.size()) {
                    tablero[i][j] = pieces.get(index);
                    index++;
                } else {
                    tablero[i][j] = null;
                }
            }
        }
    }

    private int compare(Piece a, Piece b) {
        return customOrder.get(a.name) - customOrder.get(b.name);
    }
}
