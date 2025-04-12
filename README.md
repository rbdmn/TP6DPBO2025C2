# JANJI
Saya Abdurrahman Rauf Budiman dengan NIM 2301102 mengerjakan Tugas Praktikum 6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program
Tugas Praktikum 6 ini merupakan implementasi membuat game sederhana yang cukup populer pada jaman dahulu, yaitu game `Flappy Bird`. User harus mengontrol burung yang harus melewati celah di antara pipa tanpa menyentuhnya. Berikut beberapa poin penting pada desain program ini:

## 1. Struktur Direktori
![image](https://github.com/user-attachments/assets/73dcfd19-2b9e-453b-b411-b85b2836d65e)
Keterangan:
- App.java = Bertindak sebagai titik awal aplikasi dan menampilkan menu utama sebelum permainan dimulai.
- FlappyBird.java = Merupakan panel utama permainan. Seluruh logika permainan serta konfigurasi visual dan mekanik berada dalam file ini.
- Pipe.java = Representasi class untuk pipa
- Player.java = Representasi class untuk pemain atau burungnya
- Folder Asset = Folder ini untuk menyimpan gambar untuk visualisasi pada programnya

## 2. Penjelasan Detail tiap file

### 1. App.java
Di `App.java` ada 2 metode yang dipakai, yaitu `main` dan `startGame`. Berikut penjelasan kedua method:
- Method `main` ini merupakan titik masuk program dijalankan. Lalu disini juga ada frame/window utama sebelum dimulai ke game nya, di dalam frame ini ada tombol [`start da gaem`] untuk memulai gamenya dan pindah ke frame/window baru yang ada di method `startGame`. Di dalam method ini juga ada method `paintComponent`. Method ini intinya untuk custom rendering buat background pada frame/windownya.
- Method `startGame` ini merupakan frame/window untuk permainan gamenya langsung.

### 2. FlappyBird.java
Jadi di `FlappyBird.java` ini, komponen utamanya ada ukuran layar, gambar-gambar buat background dan objek, pemain (si burung), pipa-pipa, timer buat ngatur jalan game dan munculnya pipa, sama status game (masih main atau udah game over). Terus, di konstruktor disetting panel gamenya, input dari keyboard, sama label skor juga. Method-method pentingnya ada placePipes, draw, move, resetGame, dan keyPressed. Nah, buat ngejalanin gamenya terus-terusan dipakai ActionListener, sedangkan buat nangkep tombol dari keyboard pakai KeyListener.

### 3. Pipe.java
Class ini mewakili pipa yang jadi rintangan di game Flappy Bird. Di dalamnya ada data posisi (X, Y), ukuran (width, height), gambar pipanya (image), kecepatan gerak ke kiri (velocityX), dan status apakah pipa udah dilewati burung atau belum (passed). Class ini juga punya getter dan setter lengkap buat ngatur semua atributnya.

### 4. Player.java
Class ini buat representasi si burung (pemain utama). Data yang disimpan mirip kayak Pipe, yaitu posisi, ukuran, gambar burung (image), dan kecepatan jatuh/terbang (velocityY). Sama seperti Pipe, class ini juga punya getter dan setter supaya datanya bisa diakses dan diubah dengan mudah dari luar.

# Alur Program
Pertama tama saat di run akan muncul window sebagai main menu. Di window main menu ini baru ada tombol Start yang jika diklik akan langsung pindah window ke permainan utamanya. Jika sudah diklik game akan berjalan dan user bisa menekan spasi untuk menerbangkan burung dan melewati pipa pipa. Skor akan terus bertambah sebanyak beberapa kali burung sudah melewati 1 pipa. Jika burung jatuh ke tanah atau menyentuh pipa, permainan dikatakan sudah berakhir. Kalau ingin memaikan gamenya lagi cukup tekan 'R' di keyboard.

# Rekaman
https://github.com/user-attachments/assets/00c37626-d093-4520-8896-b2245405eb6b

