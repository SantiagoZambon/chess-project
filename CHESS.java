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
            System.out.println("Faltan parámetros. Uso: java CHESS a=<algoritmo> t=<tipo> c=<color> r=<cantidad(MAX=16)>");
            return;
        }

        String sortAlgorithm = getParameter(args, "a");
        char listType = getParameter(args, "t").charAt(0);
        char color = getParameter(args, "c").charAt(0);
        int pieceCount = Integer.parseInt(getParameter(args, "r"));


        //? Validacion (TEMPORAL)
        if (!ParameterValidator.validateParameters(sortAlgorithm, listType, color, pieceCount)) {
            System.out.println("Ordenamiento: Invalido");
            System.out.println("Tipo: Invalido");
            System.out.println("Color: Invalido");
            System.out.println("Valores: []");
            System.out.println("Valores Invalidos");
            return;
        }

        PieceFactory factory = new PieceFactory();
        List<Piece> pieces = factory.generatePieces(listType, color, pieceCount);
        System.out.println("Ordenamiento: [" + getAlgorithmName(sortAlgorithm) + "]");
        System.out.println("Tipo: [" + (listType == 'n' ? "Numerico" : "Caracter") + "]");
        System.out.println("Color: [" + (color == 'b' ? "Negras" : "Blancas") + "]");
        System.out.println("Valores: " + pieces);

        SortAlgorithm sorter = getSorter(sortAlgorithm);
        if (sorter != null) {
            sorter.sort(pieces);
            System.out.println("Ordenamiento: " + pieces);
        } else {
            System.out.println("Algoritmo de ordenamiento no válido.");
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
