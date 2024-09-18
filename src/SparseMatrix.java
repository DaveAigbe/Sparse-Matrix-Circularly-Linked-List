import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class SparseMatrix {
    private CircularLinkedList[] rowHeaders;
    private CircularLinkedList[] colHeaders;
    private CircularLinkedList[] finalRowHeaders;

    public SparseMatrix(int dimensions) {
        rowHeaders = new CircularLinkedList[dimensions];
        colHeaders = new CircularLinkedList[dimensions];
        finalRowHeaders = new CircularLinkedList[dimensions];
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

    public void printFinalRows() {
        System.out.println();
        System.out.println("Final Rows:");
        for (int i = 0; i < finalRowHeaders.length; i++) {
            System.out.print((i + 1) + ": ");
            if (finalRowHeaders[i] != null) {
                finalRowHeaders[i].printList();
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

    private void updateFinalRows(int row, int col, int val) {
        if (val != 0) {
            if (finalRowHeaders[row - 1] == null) {
                CircularLinkedList newRow = new CircularLinkedList();
                newRow.addNode(row, col, val);
                finalRowHeaders[row - 1] = newRow;
            } else {
                finalRowHeaders[row - 1].addNode(row, col, val);
            }
        }
    }

    private void addFinalRows(CircularLinkedList[] updatedRows) {
        for (int i = 0; i < updatedRows.length; i++) {
            if (updatedRows[i] != null) {
                Node current = updatedRows[i].head;
                do {
                    updateFinalRows(current.row, current.col, current.value);
                    current = current.nextNode;
                } while(current != updatedRows[i].head);
            }
        }
    }


    public void addition(SparseMatrix secondMatrix) {
        for (int i = 0; i < rowHeaders.length; i++)  {
            if (secondMatrix.rowHeaders[i] != null) {
                // Traverse through second matrix because it may have values that dont exist in the first/base matrix
                Node current = secondMatrix.rowHeaders[i].head;

                // If the head from the first matrix is null at this position, just replace it with the head node of the second matrix and move on
                if (rowHeaders[i] != null) {
                    do {
                        // Get the value from the first matrix, using the same current position of the second matrix
                        Integer value = rowHeaders[i].search(current.row, current.col);
                        if (value != null) {
                            int updatedValue = current.value + value;
                            rowHeaders[i].updateNode(current.row, current.col, updatedValue);
                        } else {
                            // If the position exists in the second but not the first matrix, add it to the first matrix
                            addToMatrix(current.row, current.col, current.value);
                        }
                        current = current.nextNode;
                    } while (current != secondMatrix.rowHeaders[i].head);
                } else {
                    rowHeaders[i] = secondMatrix.rowHeaders[i];
                }
            }
        }
        addFinalRows(rowHeaders);
    }

    private int dotProduct(int[] setOne, int[] setTwo) {
        // setOne will be organized based on rows
        // setTwo will be organized based on columns
        int sum = 0;
        for (int i = 0; i < setOne.length; i++) {
            sum += setOne[i] * setTwo[i];
        }
       return sum;
    }

    public void multiplication(SparseMatrix secondMatrix){
        // 1 row will be multiplied by all columns. There needs to be a nested for loop for this.
        // If the row is empty, no need to do multiplication on it.
        for (int i = 0; i < rowHeaders.length; i++) {
            if (rowHeaders[i] != null) {
                int[] tempRow = new int[rowHeaders.length];
                Node currentRowNode = rowHeaders[i].head;
                do {
                    tempRow[currentRowNode.col - 1] = currentRowNode.value;
                    currentRowNode = currentRowNode.nextNode;
                } while (currentRowNode != rowHeaders[i].head);


                // Same row multiplied by all the diff columns
                for (int j = 0; j < colHeaders.length; j++) {
                    int[] tempCol = new int[colHeaders.length];
                    Node currentColNode = secondMatrix.colHeaders[j].head;
                    do {
                        tempCol[currentColNode.row - 1] = currentColNode.value;
                        currentColNode = currentColNode.nextNode;
                    } while (currentColNode != secondMatrix.colHeaders[j].head);

                    // Update final list
                    int product = dotProduct(tempRow, tempCol);
                    updateFinalRows(i + 1, j + 1, product);
                }
            }

        }
    }

    public void transposition(){
        for (int i = 0; i < rowHeaders.length; i++) {
            if (rowHeaders[i] != null) {
                Node current = rowHeaders[i].head;
                do {
                    int row = current.row;
                    int col = current.col;

                    updateFinalRows(col, row, current.value);
                    current = current.nextNode;
                } while(current != rowHeaders[i].head);
            }
        }
    }

    public void scalarMultiplication(int scalar){
        for (int i = 0; i < rowHeaders.length; i++) {
            if (rowHeaders[i] != null) {
                Node current = rowHeaders[i].head;
                do {
                    current.value *= scalar;
                    current = current.nextNode;
                } while(current != rowHeaders[i].head);
            }
        }

        addFinalRows(rowHeaders);
    }

    public void writeToCSV(String fileName) {
        try (FileWriter csvWriter = new FileWriter(fileName + "_output.csv")) {
            for (int i = 0; i < finalRowHeaders.length; i++) {
                if (finalRowHeaders[i] != null) {
                    Node current = finalRowHeaders[i].head;
                    do {
                        String[] arrayNode = new String[3];

                        arrayNode[0] = String.valueOf(current.row);
                        arrayNode[1] = String.valueOf(current.col);
                        arrayNode[2] = String.valueOf(current.value);

                        csvWriter.append(String.join(",", arrayNode));
                        csvWriter.append("\n");
                        current = current.nextNode;
                    } while (current != finalRowHeaders[i].head);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
