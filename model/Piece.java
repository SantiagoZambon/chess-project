package model;

public class Piece implements Comparable<Piece> {
    public String name;
    public String color;


    public Piece(int value, char color) {
        this.name = String.valueOf(value);
        this.color = (color == 'b') ? "Black" : "White";
    }

    public Piece(char value, char color) {
        this.name = String.valueOf(value);
        this.color = (color == 'b') ? "Black" : "White";
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

}
