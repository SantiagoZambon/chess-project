package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Board {
    private Piece[][] tablero;
    private List<Piece> valores;
    private char color;

    public Board(List<Piece> valores, char color) {
        this.valores = new ArrayList<>(valores);
        this.tablero = new Piece[8][8];
        this.color = color;
        llenarTablero();
    }

    // * Método para llenar el tablero con las piezas
    private void llenarTablero() {
        List<int[]> posiciones = new ArrayList<>();

        // Crear una lista con todas las posibles posiciones del tablero
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                posiciones.add(new int[] { i, j });
            }
        }

        // Mezclar las posiciones para distribuir las piezas aleatoriamente
        Collections.shuffle(posiciones);

        // Asignar las piezas a posiciones aleatorias en la matriz
        int index = 0;
        for (Piece pieza : valores) {
            int[] pos = posiciones.get(index);
            tablero[pos[0]][pos[1]] = pieza;
            index++;
        }

        // Rellenar el resto del tablero con null si quedan posiciones sin asignar
        // piezas
        while (index < posiciones.size()) {
            int[] pos = posiciones.get(index);
            tablero[pos[0]][pos[1]] = null;
            index++;
        }
    }

    // * Devuelve la matriz que representa el tablero
    public Piece[][] getTablero() {
        return tablero;
    }

    // * Imprime el tablero
    public void imprimirTablero() {
        String[] piezas = {
                "0", "K", "Q", "T", "T", "A", "A", "C", "C",
                "P", "P", "P", "P", "P", "P", "P", "P"
        };
        String[] letras = {
                "K", "Q", "T", "T", "A", "A", "C", "C",
                "P", "P", "P", "P", "P", "P", "P", "P",
        };

        // Imprime el tablero fila por fila
        for (int i = 0; i < 8; i++) {
            System.out.println("+--++--++--++--++--++--++--++--+");
            for (int j = 0; j < 8; j++) {

                if (tablero[i][j] != null && !tablero[i][j].name.equals("0") && !tablero[i][j].name.equals("0")) {
                    String name = tablero[i][j].name;

                    if (isNumeric(name)) {
                        int value = Integer.parseInt(name);
                        System.out.print("|" + piezas[value] + color + "|");
                    } else if (isAlphabetic(name)) {
                        int value = name.charAt(0) - 'a';
                        System.out.print("|" + letras[value] + color + "|");
                    }

                } else {

                    // Imprime las casillas vacías con los colores de tablero alternados
                    if (j % 2 == 0) {
                        if (i % 2 != 0) {
                            System.out.print("|⬜|");
                        } else {
                            System.out.print("|⬛|");
                        }
                    } else {
                        if (i % 2 != 0) {
                            System.out.print("|⬛|");
                        } else {
                            System.out.print("|⬜|");
                        }
                    }
                }

            }
            System.out.println();
        }
    }

    // * Verifica cadena es numérica
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // * Verifica cadena es alfabética
    private boolean isAlphabetic(String str) {
        return str.matches("[a-p]");
    }
}
