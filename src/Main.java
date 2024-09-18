public class Main {
    public static void main(String[] args) {
        CSVReader reader = new CSVReader();
        reader.separateLines("C:\\Users\\Dave\\Documents\\Repos\\Sparse-Matrix-LL\\src\\CS3345PA1_tests\\test1_a.csv");
        // Create a sparse-matrix for the first matrix
        // Check the operation to see if there is a second matrix (If it is a or m) and create that matrix as well
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
                break;
            case "S":
                matrixOne.scalarMultiplication(reader.scalar);
                break;
            case "T":
                matrixOne.transposition();
                break;
        }
    }
}