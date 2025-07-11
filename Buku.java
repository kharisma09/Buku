package uas;

public class Buku {
    private int id;
    private String judul;
    private String penulis;
    private String penerbit;
    private int tahunTerbit;
    private String status;

    public Buku(int id, String judul, String penulis, String penerbit, int tahunTerbit, String status) {
        this.id = id;
        this.judul = judul;
        this.penulis = penulis;
        this.penerbit = penerbit;
        this.tahunTerbit = tahunTerbit;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    //Judul
    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    //Penulis
    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    //penerbit
    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    //Tahun Terbit
    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    //Status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
