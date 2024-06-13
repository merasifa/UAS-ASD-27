public class TransaksiRental {
    int kodeTransaksi;
    String namaPeminjam;
    int lamaPinjam;
    double totalBiaya;
    BarangRental barangRental;

    TransaksiRental(int kodeTransaksi, String namaPeminjam, int lamaPinjam, BarangRental barangRental) {
        this.kodeTransaksi = kodeTransaksi;
        this.namaPeminjam = namaPeminjam;
        this.lamaPinjam = lamaPinjam;
        this.barangRental = barangRental;
        this.totalBiaya = lamaPinjam * barangRental.biayaSewa;
    }
    
}
