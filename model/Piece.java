package model;

public class Piece implements Comparable<Piece> {
    public String name;
    public String color;

    // * Constructor para piezas numéricas
    public Piece(int value, char color) {
        this.name = String.valueOf(value);
        this.color = (color == 'b') ? "Black" : "White"; 
    }

    // * Constructor para piezas alfabéticas
    public Piece(char value, char color) {
        this.name = String.valueOf(value);
        this.color = (color == 'b') ? "Black" : "White"; 
    }

    @Override
    public int compareTo(Piece other) {
        // * Comparar piezas por valor numérico si ambos nombres son números
        if (isNumeric(this.name) && isNumeric(other.name)) {
            return Integer.parseInt(this.name) - Integer.parseInt(other.name);
        } else {
        // * Comparar por nombre alfabético si al menos uno no es numérico
            return this.name.compareTo(other.name);
        }
    }

    @Override
    public String toString() {
        return name; // Nombre de la pieza como su representación en cadena
    }

    // * Método auxiliar para verificar si un string es numérico
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
