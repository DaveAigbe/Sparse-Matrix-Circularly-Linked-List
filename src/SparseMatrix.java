public class SparseMatrix {
    private CircularLinkedList[] rowHeaders;
    private CircularLinkedList[] colHeaders;

    public SparseMatrix(int dimensions) {
        rowHeaders = new CircularLinkedList[dimensions];
        colHeaders = new CircularLinkedList[dimensions];
    }

    // Check if row/col position in array is empty
        // If it is empty -> Create a new linked list and set the node as the first value of it, add it to position in array
        // If it is not empty -> Use the addNode operation on the linked list for that position using its corresponding row/col
    public void addToMatrix(int row, int col, int val) {
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

    public void addition(SparseMatrix firstMatrix, SparseMatrix secondMatrix) {}
    public void multiplication(SparseMatrix firstMatrix, SparseMatrix secondMatrix){}
    public void transposition(){}
    public void scalarMultiplication(){}
}
