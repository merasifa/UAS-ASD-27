import java.util.ArrayList;
import java.util.Scanner;

public class RentalSystem {
    ArrayList<BarangRental> daftarKendaraan = new ArrayList<>();
    DoubleLinkedList transaksiList = new DoubleLinkedList();

    public static void main(String[] args) {
        RentalSystem system = new RentalSystem();
        system.menu();
    }

    void menu() {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Daftar Kendaraan");
            System.out.println("2. Peminjaman");
            System.out.println("3. Tampil Seluruh Transaksi");
            System.out.println("4. Urutkan Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilihan: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    daftarKendaraan();
                    break;
                case 2:
                    peminjaman(sc);
                    break;
                case 3:
                    tampilSeluruhTransaksi();
                    break;
                case 4:
                    urutkanTransaksi();
                    break;
                case 5:
                    System.out.println("Keluar dari sistem.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        } while (choice != 0);

        sc.close();
    }

    void daftarKendaraan() {
        daftarKendaraan.add(new BarangRental("S 4567 YV", "Honda Beat", "Motor", 2017, 10000));
        daftarKendaraan.add(new BarangRental("N 4511 YV", "Honda Vario", "Motor", 2018, 10000));
        daftarKendaraan.add(new BarangRental("N 1453 YV", "Honda Yaris", "Mobil", 2022, 30000));
        daftarKendaraan.add(new BarangRental("AB 4321 YV", "Honda Innova", "Mobil", 2019, 60000));
        daftarKendaraan.add(new BarangRental("B 1234 YV", "Honda Avanza", "Mobil", 2021, 25000));
        
        System.out.println("===========================================");
        System.out.println("=== Daftar Kendaraan Rental Serba Serbi  ===");
        System.out.println("============================================");
        System.out.printf("%-10s %-20s %-10s %-6s %-10s\n", "No TNKB", "Nama Kendaraan", "Jenis", "Tahun", "Biaya Sewa");
        System.out.println("============================================================");
        for (BarangRental br : daftarKendaraan) {
            System.out.printf("%-10s %-20s %-10s %-6d %-10d\n", br.noTNKB, br.namaKendaraan, br.jenisKendaraan, br.tahun, br.biayaSewa);
        }
    }

    void peminjaman(Scanner sc) {
        System.out.print("Masukkan kode kendaraan (No TNKB): ");
        String kodeKendaraan = sc.nextLine();
        System.out.print("Masukkan lama pinjam (jam): ");
        int lamaPinjam = sc.nextInt();
        sc.nextLine();
    
        BarangRental kendaraanDipilih = null;
        for (BarangRental br : daftarKendaraan) {
            if (br.noTNKB.equals(kodeKendaraan)) {
                kendaraanDipilih = br;
                break;
            }
        }
        if (kendaraanDipilih == null) {
            System.out.println("Kendaraan dengan kode " + kodeKendaraan + " tidak ditemukan.");
        } else {
            boolean isAvailable = true;
            Node current = transaksiList.head;
            while (current != null) {
                if (current.data.barangRental.noTNKB.equals(kodeKendaraan)) {
                    System.out.println("Kendaraan dengan kode " + kodeKendaraan + " sudah dipinjam oleh orang lain.");
                    isAvailable = false;
                    break;
                }
                current = current.next;
            }
    
            if (isAvailable) {
                double totalBiaya = lamaPinjam * kendaraanDipilih.biayaSewa;
    
                double potongan = 0;
                if (lamaPinjam >= 48 && lamaPinjam <= 78) {
                    potongan = totalBiaya * 0.1;
                } else if (lamaPinjam > 78) {
                    potongan = totalBiaya * 0.2; 
                }
                boolean isMember = false;
                System.out.print("Apakah Anda member? (Y/N): ");
                String jawaban = sc.nextLine().toLowerCase();
                if (jawaban.equals("Y")) {
                isMember = true;
                }
                
                if (isMember) {
                    totalBiaya -= 2500;
                }
                double totalBiayaSetelahDiskon = totalBiaya - potongan;
                System.out.print("Masukkan kode transaksi: ");
                int kodeTransaksi = sc.nextInt();
                sc.nextLine();
                System.out.print("Masukkan nama peminjam: ");
                String namaPeminjam = sc.nextLine();
    
                TransaksiRental transaksi = new TransaksiRental(kodeTransaksi, namaPeminjam, lamaPinjam, kendaraanDipilih);
                transaksi.totalBiaya = totalBiayaSetelahDiskon;
                transaksiList.add(transaksi);
    
                System.out.println("Peminjaman kendaraan berhasil.");
            }
        }
    }
    

    void tampilSeluruhTransaksi() {
        transaksiList.display();
    }

    void urutkanTransaksi() {
        transaksiList.sort();
        System.out.println("Transaksi berhasil diurutkan.");
    }
}
