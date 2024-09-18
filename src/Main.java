//TODO: Output finalRows to CSV

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Read user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter full file path, starting from drive: ");
        String file = sc.nextLine();

        CSVReader reader = new CSVReader();
        reader.separateLines(file);
        // Create a sparse-matrix for the first matrix
        // Execute proper operations with their inputs based on the operation
        SparseMatrix matrixOne = new SparseMatrix(reader.dimension);
        matrixOne.createMatrix(reader.matrixOne);
        
        SparseMatrix matrixTwo = null;

        if (reader.operation.equals("A") || reader.operation.equals("M")) {
            matrixTwo = new SparseMatrix(reader.dimension);
            matrixTwo.createMatrix(reader.matrixTwo);
        }

        switch (reader.operation) {
            case "A":
                matrixOne.addition(matrixTwo);
                matrixOne.printFinalRows();
                break;
            case "M":
                matrixOne.multiplication(matrixTwo);
                matrixOne.printFinalRows();
                break;
            case "S":
                matrixOne.scalarMultiplication(reader.scalar);
                matrixOne.printFinalRows();
                break;
            case "T":
                matrixOne.transposition();
                matrixOne.printFinalRows();
                break;
        }
    }
}