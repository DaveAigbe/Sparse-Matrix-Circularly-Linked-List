public class CircularLinkedList {
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

    public void printList() {
        Node current = head;

            do {
                current.printNode();
                System.out.print(" → ");
                current = current.nextNode;
            } while (current != head);

            System.out.print("HEAD");
    }

    public int search(int row, int col) {
        return 0;
    }

}
