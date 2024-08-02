
//! Main
import java.util.List;
import model.Piece;
import model.PieceFactory;
import utils.ParameterValidator;
import algorithms.SortAlgorithm;
import algorithms.InsertionSort;
import algorithms.QuickSort;

public class CHESS {

    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println(
                    "Faltan parámetros. Uso: java CHESS a=<algoritmo (i or q)> t=<tipo (n or a)> c=<color (b or w)> r=<cantidad(MAX=16)>");
            return;
        }

        String sortAlgorithm = getParameter(args, "a");
        char listType = getParameter(args, "t").charAt(0);
        char color = getParameter(args, "c").charAt(0);
        int pieceCount = Integer.parseInt(getParameter(args, "r"));

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
            SortAlgorithm sorter = getSorter(sortAlgorithm);
            if (sorter != null) {
                sorter.sort(pieces);
                System.out.println("Ordenamiento: " + pieces);
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
            default:
                return "Invalid sort";
        }
    }
}
