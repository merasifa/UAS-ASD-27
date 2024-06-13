class Node {
    TransaksiRental data;
    Node next;
    Node prev;

    Node(TransaksiRental data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
