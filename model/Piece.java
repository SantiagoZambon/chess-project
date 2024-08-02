package model;

public class Piece implements Comparable<Piece> {
    public String name;
    public String color;
    public int positionX;
    public int positionY;

    public Piece(int value, char color) {
        this.name = String.valueOf(value);
        this.color = (color == 'b') ? "Black" : "White";
        this.positionX = 0;
        this.positionY = 0;
    }

    public Piece(char value, char color) {
        this.name = String.valueOf(value);
        this.color = (color == 'b') ? "Black" : "White";
        this.positionX = 0;
        this.positionY = 0;
    }

    @Override
    public int compareTo(Piece other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString() {
        return name;
    }
}
