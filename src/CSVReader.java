import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;

public class CSVReader {
    String[] lines;
    String operation;
    int dimension;
    int[][] matrixOne;
    int[][] matrixTwo = null;
    int scalar;


    public void separateLines(String csvFile) {
        try {
            String content = Files.readString(Paths.get(csvFile));
            this.lines = content.strip().split(",,");;

            extractValues();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void extractValues() {
        extractOperations();
        this.matrixOne = extractMatrix(lines[1]);

        if (operation.equals("A") || operation.equals("M")) {
            this.matrixTwo = extractMatrix(lines[2]);
        } else if (operation.equals("S")) {
            scalar = Integer.parseInt(lines[2].strip());
        }
    }

    private void extractOperations() {
        String[] operations = this.lines[0].split(",");
        this.operation = operations[0];
        this.dimension = Integer.parseInt(operations[1]);
    }

    private int[][] extractMatrix(String matrix) {
            // Order these by the column, so use the 2nd index of each matrix set to order that array
            matrix = matrix.trim();
            String[] matrixStringParts = matrix.split(System.lineSeparator());
            int[][] matrixParts = new int[matrixStringParts.length][3];
            for (int i = 0; i < matrixStringParts.length; i++) {
                matrixStringParts[i] = matrixStringParts[i].trim();
                String[] part = matrixStringParts[i].split(",");
                matrixParts[i] = Arrays.stream(part).mapToInt(Integer::parseInt).toArray();
            }

            Arrays.sort(matrixParts, Comparator.comparingInt(a -> a[1]));

            return matrixParts;
    }
}
