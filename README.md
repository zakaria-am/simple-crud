# 1. Database
Pastikan beberapa hal berikut ini sudah terpasang di local :
- MySQL yang berjalan di localhost
- MySQL Workbench
- WinZIP atau semacamnya
- Browser

Buka Browser lalu buka link berikut `https://github.com/datacharmer/test_db` untuk mendapatkan sql database.

![image](https://user-images.githubusercontent.com/111683454/185790030-bcb563b3-bd09-4075-bbcf-bdc90ca6dfba.png)

Kemudian ekstrak ke local, contohnya diekstrak ke path `C:\`

![image](https://user-images.githubusercontent.com/111683454/185790165-9d01824d-2079-4934-9522-0c0cd28c6d76.png)

Lalu buka `Command Prompt` dan running sebagai `Administrator`, arahkan ke path hasil ekstrak tadi. 

Kemudian jalankan perintah ` mysql -uroot -p -t < employees.sql `

![image](https://user-images.githubusercontent.com/111683454/185790324-0fa98772-61d3-46d4-a8cc-4196551570f6.png)

Setelah selesai, buka aplikasi MySQL Workbench, lalu buka Local Instance.

![image](https://user-images.githubusercontent.com/111683454/185790503-689562d4-ef34-48e8-8cdc-4fb9b495725d.png)

Pastikan schema `employees` sudah ada.

![image](https://user-images.githubusercontent.com/111683454/185790568-b9f9061d-1aa9-4193-9de7-862ae33779d1.png)

# 2. Aplikasi
Pastikan beberapa hal berikut ini sudah terpasang di local :
- IntelliJ IDEA atau IDE sejenis.
- Git

Pull pada IDE menggunakan link git berikut `https://github.com/zakaria-am/simple-crud.git`

Ubah `Run/Debug Configuration` menggunakan perintah `spring-boot:run`

![image](https://user-images.githubusercontent.com/111683454/185791180-6e46369e-3ac3-4fe3-af99-1b628ff987a0.png)

Kemudian klik `Run` untuk menyalakan aplikasi. Tunggu sampai dalam log muncul ini.

![image](https://user-images.githubusercontent.com/111683454/185791307-77d5729b-1e7c-4f69-a78c-0a9be5833904.png)



