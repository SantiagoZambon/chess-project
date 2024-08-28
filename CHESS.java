
//! Main
import java.util.List;
import java.util.Random;

import model.Board;
import model.Piece;
import model.PieceFactory;
import utils.ParameterValidator;
import algorithms.SortAlgorithm;
import algorithms.BubbleSort;
import algorithms.InsertionSort;
import algorithms.QuickSort;

public class CHESS {

    public static void main(String[] args) {
        if (args.length != 4 && args.length != 3 && args.length != 2) {
            System.out.println(
                    "Faltan parámetros. Uso: java CHESS a=<algoritmo (i or q)> t=<tipo (n or a)> c=<color (b or w)> r=<cantidad (MAX=16)>");
            return;
        }

        // * Si a=null se escoge aleatoriamente el algoritmo
        Random random = new Random();
        String[] algorithms = { "i", "q", "b" };
        String sortAlgorithm = (getParameter(args, "a") != null && !getParameter(args, "a").isEmpty())
                ? getParameter(args, "a")
                : algorithms[random.nextInt(algorithms.length)];

        char listType = getParameter(args, "t").charAt(0);
        char color = getParameter(args, "c").charAt(0);

        // * Si r=null entonces r=16
        int pieceCount = (getParameter(args, "r") != null && !getParameter(args, "r").isEmpty())
                ? Integer.parseInt(getParameter(args, "r"))
                : 16;

        // * Validaciones
        if (ParameterValidator.validateAlgorithms(sortAlgorithm)) {
            System.out.println("Ordenamiento: [" + getAlgorithmName(sortAlgorithm) + "]");
        } else {
            System.out.println("Ordenamiento: Invalido");
        }

        if (ParameterValidator.validateType(listType)) {
            System.out.println("Tipo: [" + (listType == 'n' ? "Numerico" : "Caracter") + "]");
        } else {
            System.out.println("Tipo: Invalido");
        }

        if (ParameterValidator.validateColors(color)) {
            System.out.println("Color: [" + (color == 'b' ? "Negras" : "Blancas") + "]");
        } else {
            System.out.println("Color: Invalido");
        }

        if (ParameterValidator.validateRange(pieceCount)) {
            PieceFactory factory = new PieceFactory();
            List<Piece> pieces = factory.generatePieces(listType, color, pieceCount);
            System.out.println("Valores: " + pieces);
            // Creamos una instancia de Board
            Board board = new Board(pieces, color);
            // Imprimimos el tablero
            board.imprimirTablero();
            SortAlgorithm sorter = getSorter(sortAlgorithm);

            if (sorter != null) {
                sorter.sort(board.getTablero());
                System.out.println("\n");
                board.imprimirTablero();
            } else {
                System.out.println("Ordenamiento: []");
            }
        } else {
            System.out.println("Valores: Invalidos");
            System.out.println("Ordenamiento: []");
        }

    }

    private static String getParameter(String[] args, String key) {
        for (String arg : args) {
            if (arg.startsWith(key + "=")) {
                return arg.split("=")[1];
            }
        }
        return "";
    }

    private static SortAlgorithm getSorter(String sortAlgorithm) {
        switch (sortAlgorithm.toLowerCase()) {
            case "i":
                return new InsertionSort();
            case "q":
                return new QuickSort();
            case "b":
                return new BubbleSort();
            default:
                return null;
        }
    }

    private static String getAlgorithmName(String sortAlgorithm) {
        switch (sortAlgorithm.toLowerCase()) {
            case "i":
                return "Insertion sort";
            case "q":
                return "Quick sort";
            case "b":
                return "Bubble sort";
            default:
                return "Invalid sort";
        }
    }
}
