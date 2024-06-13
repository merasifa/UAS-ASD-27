import java.util.Comparator;

class DoubleLinkedList {
    Node head;
    Node tail;

    DoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    void add(TransaksiRental data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    void display() {
        System.out.println("\n===============================================================================================================");
        System.out.printf("| %-12s | %-20s | %-10s | %-15s | %-20s | %-15s |\n", 
                          "Kode Transaksi", "Nama Peminjam", "Lama Pinjam", "Total Biaya", "No TNKB", "Nama Kendaraan");
        System.out.println("===============================================================================================================");
        Node current = head;
        while (current != null) {
            System.out.printf("| %-12d | %-20s | %-10d | Rp %-13.2f | %-20s | %-15s |\n", 
                              current.data.kodeTransaksi, current.data.namaPeminjam, 
                              current.data.lamaPinjam, current.data.totalBiaya, 
                              current.data.barangRental.noTNKB, current.data.barangRental.namaKendaraan);
            current = current.next;
        }
        System.out.println("===============================================================================================================");
    }
    

    void sort() {
        if (head == null) return;

        Node current = head, index = null;
        TransaksiRental temp;
        while (current != null) {
            index = current.next;
            while (index != null) {
                if (current.data.kodeTransaksi > index.data.kodeTransaksi) {
                    temp = current.data;
                    current.data = index.data;
                    index.data = temp;
                }
                index = index.next;
            }
            current = current.next;
        }
    }

    


}

