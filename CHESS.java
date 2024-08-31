
//! Main
import java.util.List;
import java.util.Random;

import model.Board;
import model.Piece;
import model.PieceFactory;

import utils.ParameterValidator;

import algorithms.SortAlgorithm;
import algorithms.Algorithms;

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
                pieceCount = 16; // Valor por defecto
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
                speed = 500; // Valor por defecto
            }
        } catch (NumberFormatException e) {
            speed = 0;
        }

        // ? VALIDACIONES
        // * Validacion algotirmo de ordenamiento
        if (!ParameterValidator.validateAlgorithms(sortAlgorithm)) {
            System.out.println("Ordenamiento: Seleccione uno de los tres disponibles <b, i, q>");
            return;
        }

        // * Validacion tipo de lista
        if (!ParameterValidator.validateType(listType)) {
            System.out.println("Tipo: Seleccione tipo numerico <n> o tipo caracteres <c>");
            return;
        }

        // * Validacion color b o w
        if (!ParameterValidator.validateColors(color)) {
            System.out.println("Color: Seleccione black <b> o white <w>");
            return;
        }

        // * Validacion cantidad de piezas
        if (!ParameterValidator.validateRange(pieceCount)) {
            System.out.println("Valores: Selecciones un numero entre 1 y 16");
            return;
        }

        // * Validacion cantidad de piezas
        if (!ParameterValidator.validateSpeed(speed)) {
            System.out.println("Velocidad: Selecciones un numero entre 100 y 1000");
            return;
        }

        // ? MOSTRAR CONFIGURACION
        System.out.println("CONFIGURACION:");
        System.out.println("Algoritmo: [" + getAlgorithmName(sortAlgorithm) + "]");
        System.out.println("Tipo: [" + (listType == 'n' ? "Numerico" : "Caracter") + "]");
        System.out.println("Color: [" + (color == 'b' ? "Negras" : "Blancas") + "]");
        System.out.println("Velocidad: [" + speed + "ms]");

        PieceFactory factory = new PieceFactory();
        List<Piece> pieces = factory.generatePieces(listType, color, pieceCount);
        System.out.println("Valores: " + pieces);

        // ? TABLERO
        Board board = new Board(pieces, color);

        board.imprimirTablero();

        // ?ALGORITMO
        SortAlgorithm sorter = getSorter(sortAlgorithm);

        if (sorter != null) {
            // Ejecutar algoritmo
            sorter.sort(board.getTablero(), speed, color);
            System.out.println("\nTablero Ordenado:");
            board.imprimirTablero();
        }
    }

    // ? FUNCIONES
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
                return new Algorithms("i");
            case "q":
                return new Algorithms("q");
            case "b":
                return new Algorithms("b");
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
