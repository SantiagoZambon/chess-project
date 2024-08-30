
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
        if (args.length < 2 || args.length > 5) {
            System.out.println(
                    "Faltan parámetros. Uso: java CHESS a=<algoritmo (i or q)> t=<tipo (n or a)> c=<color (b or w)> r=<cantidad (MAX=16)> s=<velocidad en ms>");
            return;
        }

        // ? PARÁMETROS
        // * Parametro "a" (Algoritmo de ordenamiento)
        Random random = new Random();
        String[] algorithms = { "i", "q", "b" };
        String sortAlgorithm = (getParameter(args, "a") != null && !getParameter(args, "a").isEmpty())
                ? getParameter(args, "a")
                : algorithms[random.nextInt(algorithms.length)];

        // * Parametro "t" (Tipo de lista)
        char listType = getParameter(args, "t").charAt(0);

        // * Parametro "c" (Color de la pieza)
        char color = getParameter(args, "c").charAt(0);

        // * Prametro "r" (Cantidad de piezas en el tablero)
        int pieceCount;
        try {
            String param = getParameter(args, "r");
            if (param != null && !param.isEmpty()) {
                pieceCount = Integer.parseInt(param);
            } else {
                pieceCount = 16; // Valor por defecto si no se proporciona el parámetro "r"
            }
        } catch (NumberFormatException e) {
            pieceCount = 0;
        }

        // * Parametro "s" (Velocidad de ordenamiento)
        int speed;
        try {
            String speedParam = getParameter(args, "s");
            if (speedParam != null && !speedParam.isEmpty()) {
                speed = Integer.parseInt(speedParam);
            } else {
                speed = 500; // Valor por defecto si no se proporciona un parámetro "s"
            }
        } catch (NumberFormatException e) {
            speed = 0;
        }

        // ? VALIDACIONES
        // * Validacion algotirmo de ordenamiento
        if (!ParameterValidator.validateAlgorithms(sortAlgorithm)) {
            System.out.println("Ordenamiento: Invalido");
            return;
        }

        // * Validacion tipo de lista
        if (!ParameterValidator.validateType(listType)) {
            System.out.println("Tipo: Invalido");
            return;
        }

        // * Validacion color b o w
        if (!ParameterValidator.validateColors(color)) {
            System.out.println("Color: Invalido");
            return;
        }

        // * Validacion cantidad de piezas
        if (!ParameterValidator.validateRange(pieceCount)) {
            System.out.println("Valores: Invalidos");
            return;
        }

        // * Validacion cantidad de piezas
        if (!ParameterValidator.validateSpeed(speed)) {
            System.out.println("Velocidad: Invalidos");
            return;
        }

        // ? MOSTRAR CONFIGURACION
        System.out.println("Ordenamiento: [" + getAlgorithmName(sortAlgorithm) + "]");
        System.out.println("Tipo: [" + (listType == 'n' ? "Numerico" : "Caracter") + "]");
        System.out.println("Color: [" + (color == 'b' ? "Negras" : "Blancas") + "]");

        PieceFactory factory = new PieceFactory();
        List<Piece> pieces = factory.generatePieces(listType, color, pieceCount);
        System.out.println("Valores: " + pieces);

        // Creamos una instancia de Board
        Board board = new Board(pieces, color);

        // Imprimimos el tablero
        board.imprimirTablero();
        SortAlgorithm sorter = getSorter(sortAlgorithm);

        if (sorter != null) {
            sorter.sort(board.getTablero(), speed, color);
            System.out.println("\nTablero Ordenado:");
            board.imprimirTablero();
        } else {
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
