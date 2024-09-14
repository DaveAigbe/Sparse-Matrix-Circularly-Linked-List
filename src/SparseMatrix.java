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
    public void addToMatrix() {


    }

    public void addition(){}
    public void multiplication(){}
    public void transposition(){}
    public void scalarMultiplication(){}
}
