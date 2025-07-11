# Aplikasi Manajemen Buku

Aplikasi sederhana berbasis JavaFX untuk mengelola daftar buku.
Pengguna dapat menambahkan, mengedit, menghapus, dan melihat daftar buku dengan detail seperti judul, penulis, penerbit, tahun terbit, dan status.

## Fitur

* Menambahkan data buku baru
* Memperbarui data buku yang sudah ada
* Menghapus data buku
* Menampilkan semua data buku dalam tabel
* Memilih buku untuk diedit atau dihapus
* Validasi input dan pesan kesalahan jika ada field kosong atau salah
* Dropdown untuk memilih status buku: Tersedia, Dipinjam, Hilang

## Teknologi

* Java 17 atau lebih baru
* JavaFX
* IDE (seperti IntelliJ IDEA, Eclipse, atau Visual Studio Code)


## Cara Menjalankan

1. Clone repository ini:

```
git clone https://github.com/username/buku-management.git
```

2. Buka project di IDE yang Anda gunakan.
   Pastikan library JavaFX sudah diatur dengan benar.

3. Jalankan file `Main.java`.
   Aplikasi akan terbuka dan siap digunakan.

## Data Buku

Setiap buku memiliki data berikut:

* ID (otomatis)
* Judul
* Penulis
* Penerbit
* Tahun Terbit
* Status: Tersedia, Dipinjam, Hilang
