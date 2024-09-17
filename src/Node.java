public class Node {
    int row, col, value;
    Node nextNode = null;

    Node(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public void printNode() {
        System.out.print("(" + row + ", " + col + ", " + value + ")");
    }
}
