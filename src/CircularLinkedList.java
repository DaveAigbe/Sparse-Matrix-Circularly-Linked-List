public class CircularLinkedList {
    public Node head = null;
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

    public Integer search(int row, int col) {
        Node current = head;

        do {
            if (current.row == row && current.col == col) {
                return current.value;
            }
            current = current.nextNode;
        } while (current != head);

        return null;
    }

    public void updateNode(int row, int col, int data) {
        Node current = head;

        do {
            if (current.row == row && current.col == col) {
                current.value = data;
            }
            current = current.nextNode;
        } while (current != head);
    }

}
