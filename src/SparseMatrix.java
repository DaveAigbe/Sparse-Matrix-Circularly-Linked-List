import java.util.Arrays;

public class SparseMatrix {
    private CircularLinkedList[] rowHeaders;
    private CircularLinkedList[] colHeaders;

    public SparseMatrix(int dimensions) {
        rowHeaders = new CircularLinkedList[dimensions];
        colHeaders = new CircularLinkedList[dimensions];
    }

    public void createMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
                int row = matrix[i][0];
                int col = matrix[i][1];
                int value = matrix[i][2];
                addToMatrix(row, col, value);
        }
    }

    public void printRows() {
        System.out.println();
        System.out.println("Rows:");
        for (int i = 0; i < rowHeaders.length; i++) {
            System.out.print(i + ": ");
            if (rowHeaders[i] != null) {
                rowHeaders[i].printList();

            }
            System.out.println();
        }
    }

    public void printCols() {
        System.out.println();
        System.out.println("Cols:");
        for (int i = 0; i < colHeaders.length; i++) {
            System.out.print(i + ": ");
            if (colHeaders[i] != null) {
                colHeaders[i].printList();
            }
            System.out.println();
        }
    }

    private void addToMatrix(int row, int col, int val) {
        if (rowHeaders[row - 1] == null) {
            CircularLinkedList newRow = new CircularLinkedList();
            newRow.addNode(row, col, val);
            rowHeaders[row - 1] = newRow;
        } else {
            rowHeaders[row - 1].addNode(row, col, val);
        }

        if (colHeaders[col - 1] == null) {
            CircularLinkedList newCol = new CircularLinkedList();
            newCol.addNode(row, col, val);
            colHeaders[col - 1] = newCol;
        } else {
            colHeaders[col - 1].addNode(row, col, val);
        }
    }



    public void addition(SparseMatrix secondMatrix) {}
    public void multiplication(SparseMatrix secondMatrix){}
    public void transposition(){}
    public void scalarMultiplication(int scalar){}
}
