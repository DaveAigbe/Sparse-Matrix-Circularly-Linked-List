public class CircularLinkedList {
    // TODO: The linked list will be used as the rows and columns whos row/col headers will be stored in a array with n dimension which is provided in the csv
    private Node head = null;
    private Node tail = null;

    public void addNode(int row, int col, int data) {
        Node newNode = new Node(row, col, data);

        if (head == null) {
            head = newNode;
        } else {
            tail.nextNode = newNode;
        }
        tail = newNode;
        tail.nextNode = head;
    }

    public int search(int row, int col) {
        return 0;
    }

}
