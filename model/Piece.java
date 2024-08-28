package model;

public class Piece implements Comparable<Piece> {
    public String name;
    public String color;
    // public int positionX;
    // public int positionY;

    public Piece(int value, char color) {
        this.name = String.valueOf(value);
        this.color = (color == 'b') ? "Black" : "White";
        // this.positionX = x;
        // this.positionY = y;
    }

    public Piece(char value, char color) {
        this.name = String.valueOf(value);
        this.color = (color == 'b') ? "Black" : "White";
        // this.positionX = x;
        // this.positionY = y;
    }

    @Override
    public int compareTo(Piece other) {
        if (isNumeric(this.name) && isNumeric(other.name)) {
            return Integer.parseInt(this.name) - Integer.parseInt(other.name);
        } else {
            return this.name.compareTo(other.name);
        }
    }

    @Override
    public String toString() {
        return name;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // private String getPieceName(int value) {
    // switch (value) {
    // case 1:
    // return "K"; // Rey
    // case 2:
    // return "Q"; // Reina
    // case 3:
    // case 4:
    // return "T"; // Torre
    // case 5:
    // case 6:
    // return "A"; // Alfil
    // case 7:
    // case 8:
    // return "C"; // Caballo
    // default:
    // return "P"; // Peón
    // }
    // }

}
