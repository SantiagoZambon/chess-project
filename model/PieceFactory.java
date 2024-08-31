package model;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {

    public List<Piece> generatePieces(char listType, char color, int pieceCount) {
        List<Piece> pieces = new ArrayList<>();

        int[] valoresNum = {};
        char[] valoresCar = {};

        if (pieceCount == 1 || pieceCount == 2) {
            valoresNum = new int[] { 0, 0, 0, 1, 2 };
            valoresCar = new char[] { '0', '0', '0', 'a', 'b' };
            pieceCount += 3;
        } else if (pieceCount == 3 || pieceCount == 4) {
            valoresNum = new int[] { 0, 0, 1, 2, 5, 6 };
            valoresCar = new char[] { '0', '0', 'a', 'b', 'e', 'f' };
            pieceCount += 2;
        } else if (pieceCount == 5 || pieceCount == 6) {
            valoresNum = new int[] { 0, 1, 2, 5, 6, 7, 8 };
            valoresCar = new char[] { '0', 'a', 'b', 'e', 'f', 'g', 'h' };
            pieceCount += 1;
        } else if (pieceCount == 7 || pieceCount == 8) {
            valoresNum = new int[] { 1, 2, 5, 6, 7, 8, 3, 4 };
            valoresCar = new char[] { 'a', 'b', 'e', 'f', 'g', 'h', 'c', 'd' };
        } else {
            valoresNum = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
            valoresCar = new char[] { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p' };
        }

        if (listType == 'n') {
            for (int i = 0; i < pieceCount; i++) {
                pieces.add(new Piece(valoresNum[i], color));
            }
        } else if (listType == 'c') {
            for (int i = 0; i < pieceCount; i++) {
                pieces.add(new Piece(valoresCar[i], color));
            }
        }
        return pieces;
    }
}
