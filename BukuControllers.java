package uas;

import java.util.ArrayList;
import java.util.List;

public class BukuControllers {
    private List<Buku> daftarBuku = new ArrayList<>();
    private int nextId = 1;

    public void tambahBuku(String judul, String penulis, String penerbit, int tahunTerbit, String status) {
        Buku buku = new Buku(nextId++, judul, penulis, penerbit, tahunTerbit, status);
        daftarBuku.add(buku);
    }

    public List<Buku> getAll() { return daftarBuku; }

    public Buku cariById(int id) {
        return daftarBuku.stream().filter(b -> b.getId() == id).findFirst().orElse(null);
    }

    public boolean updateBuku(int id, String judulBaru, String penulisBaru, String penerbitBaru, int tahunTerbitBaru, String statusBaru) {
        Buku buku = cariById(id);
        if (buku != null) {
            buku.setJudul(judulBaru);
            buku.setPenulis(penulisBaru);
            buku.setPenerbit(penerbitBaru);
            buku.setTahunTerbit(tahunTerbitBaru);
            buku.setStatus(statusBaru);
            return true;
        }
        return false;
    }

    public boolean hapusBuku(int id) {
        Buku buku = cariById(id);
        return buku != null && daftarBuku.remove(buku);
    }
}
