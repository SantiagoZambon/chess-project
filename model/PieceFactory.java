package model;

import java.util.ArrayList;
import java.util.List;

public class PieceFactory {

    public List<Piece> generatePieces(char listType, char color, int pieceCount) {
        List<Piece> pieces = new ArrayList<>();
        if (listType == 'n') {
            int[] valores = { 5, 8, 15, 16, 1, 2, 9, 10, 11, 6, 7, 13, 14, 3, 4, 12 };
            for (int i = 0; i < pieceCount; i++) {
                pieces.add(new Piece(valores[i], color));
            }
        } else if (listType == 'c') {
            char[] valores = { 'm', 'j', 'k', 'l', 'e', 'n', 'c', 'd', 'b', 'g', 'h', 'i', 'f', 'o', 'p', 'a' };
            for (int i = 0; i < pieceCount; i++) {
                pieces.add(new Piece(valores[i], color));
            }
        }
        return pieces;
    }
}
