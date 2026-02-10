Modul 1 - Coding Standards  
Azzahra Anjelika Borselano  
2406419663  

# Reflection 1

_Prinsip Clean Code dan Secure Coding yang Diterapkan_  

**1. Penggunaan nama yang jelas**  
Saya menggunakan nama variabel, method, dan class yang deskriptif sehingga fungsinya dapat dipahami tanpa perlu komentar penjelasan.

**2. DRY – Don’t Repeat Yourself**  
Untuk mengurangi redundansi, saya memanfaatkan kembali method yang sudah ada. Dengan cara ini, logika yang sama tidak ditulis berulang kali, sehingga kode menjadi lebih efisien.

**3. Penerapan Single Responsibility Principle**  
Struktur aplikasi dibagi ke dalam beberapa layer, yaitu Controller, Service, dan Repository. Setiap class hanya memiliki satu tanggung jawab utama. Pemisahan ini membuat kode lebih terstruktur.

**4. Fungsi fokus pada satu tugas**  
Setiap method dibuat sesederhana mungkin dan hanya menangani satu tujuan spesifik. Contohnya, method delete hanya bertugas untuk menghapus produk tanpa menangani logika lain di luar itu.

_Aspek yang Masih Perlu Dikembangkan_  
Saat ini, input yang tidak valid seperti nama produk kosong atau jumlah bernilai negatif masih diterima oleh aplikasi. Untuk mengatasinya, perlu ditambahkan logika validasi pada model Product.

# Reflection 2

1. Unit test membantu saya memahami bagaimana program bekerja dalam berbagai kondisi, termasuk edge cases. Dengan adanya unit test, kesalahan dapat terdeteksi lebih awal sebelum kode digunakan lebih jauh. 
Jumlah unit test yang perlu dibuat sangat bergantung pada kompleksitas suatu class serta jumlah method yang dimiliki. Idealnya, setiap method memiliki setidaknya satu unit test, dan untuk method yang 
lebih kompleks, diperlukan beberapa test dengan variasi input yang berbeda. Untuk memastikan bahwa unit test yang dibuat sudah cukup, salah satu cara yang dapat digunakan adalah dengan melihat code coverage. 
Code coverage menunjukkan seberapa besar bagian dari source code yang dijalankan saat pengujian. Dengan melihat hasil code coverage, kita dapat mengetahui bagian mana dari kode yang belum teruji dan perlu ditest. 
Namun, memiliki 100% code coverage tidak berarti kode tersebut bebas dari bug atau kesalahan. Code coverage hanya memastikan bahwa baris kode dieksekusi, bukan menjamin bahwa logika di dalamnya sudah benar atau 
sesuai dengan kebutuhan yang diharapkan.

2. Dari segi clean code, cara tersebut kurang optimal karena banyak melakukan salinan terhadap kode sebelumnya. Pengulangan kode yang sama di beberapa kelas test dapat menyebabkan masalah pada saat pemeliharaan. 
Sebagai contoh, jika terjadi perubahan pada konfigurasi aplikasi atau cara inisialisasi pengujian, maka setiap test suite harus diperbarui secara manual. Hal ini tidak hanya memakan waktu, tetapi juga meningkatkan 
kemungkinan adanya bagian kode yang terlewat atau tidak sinkron satu sama lain. Untuk mengatasi hal tersebut, pendekatan yang lebih baik adalah dengan mengekstrak logika yang bersifat umum ke dalam satu tempat, 
seperti kelas induk untuk functional test atau utilitas khusus untuk setup. Dengan memusatkan kode yang digunakan bersama, struktur test menjadi lebih jelas, konsisten, dan mudah dikembangkan. Selain itu, cara ini 
juga membantu menjaga kualitas kode pengujian seiring bertambahnya jumlah test di dalam proyek.