# DSL Penjadwalan

Domain Class Scheduling membahas tentang mekanisme penjadwalan suatu mata kuliah pada ruangan tertentu. Penentuan jadwal tersebut dilakukan pula dengan meninjau kebutuhan yang harus disediakan oleh suatu ruangan demi mendukung keberjalanan perkuliahan. 

### Cara Penggunaan

  - Memasukkan perintah sesuai dengan tata bahasa yang telah didefinisikan sebelumnya pada *command line*
  - Pengguna dapat memasukkan command sebagai berikut:
  	- `showRoom` untuk menampilkan daftar ruangan yang ada
  	- `showCourse` untuk menampilkan daftar mata kuliah yang ada
  	- `showClass` untuk menampilkan daftar kelas dan jam
  	- `showSchedule` untuk menampilkan semua daftar jadwal yang terbentuk

  - Berikut ini tata bahasa yang digunakan pada sistem DSL ini.


```sh
ROOM NAME <nama ruangan>
	 CAPACITY <kapasitas>
	 FACILITY <[fasilitas 1, fasilitas 2, …]>

COURSE NAME <nama mata kuliah>
	   ID <ID mata kuliah>
	   LECTURER <nama pengajar>
	   REQUIREMENT <[fasilitas 1, fasilitas 2, …]>
	   CAPACITY <kapasitas>
	   CONSTRAINT DAY <hari> AT START <int> DURATION <int>
	   PREFERENCE DAY <hari> AT START <int> DURATION <int>

CLASS ID <ID mata kuliah>
	  ROOM <nama ruangan>
	  TIME DAY <hari> AT START <int> DURATION <int>
	  
DELETE CLASS
	  ID <ID mata kuliah>
	  ROOM <nama ruangan>
	  TIME DAY <hari> AT START <int> DURATION <int>
```

  - Berikut ini adalah contoh masukan yang diterima oleh program. Apabila masukan tidak sesuai dengan grammar dan nilai yang ada pada sistem maka akan muncul pesal error.

```sh
ROOM NAME 7607 
	 CAPACITY 100 
	 FACILITY projector screen board
	 
COURSE NAME NLP
	   ID IF4072
	   LECTURER DPL
	   REQUIREMENT projector screen
	   CAPACITY 100
	   CONSTRAINT DAY monday AT START 1 DURATION 6
	   PREFERENCE DAY tuesday AT START 6 DURATION 5

CLASS ID IF4072
	  ROOM 7607
	  TIME DAY tuesday AT START 7 DURATION 2
	  
DELETE CLASS
	  ID IF4072
	  ROOM 7607
	  TIME DAY tuesday AT START 7 DURATION 2
```

### Daftar Istilah

Berikut ini adalah daftar istilah yang digunakan pada sistem ini.

| Istilah | Deskripsi |
| ------ | ------ |
| Class | Class merupakan hasil dari penjadwalan yang memenuhi constraint dari seorang lecturer dan memenuhi requirement yang diajukan oleh suatu course.  |
| Facilities |Facilities merupakan fasilitas-fasilitas yang disediakan oleh suatu room. |
| Requirements | Requirement merupakan kebutuhan-kebutuhan yang harus dipenuhi untuk menyelenggarakan suatu course. |
| Room | Room merupakan ruangan yang dialokasikan untuk melaksanakan perkuliahan. |
| Schedule | Schedule merupakan jadwal perkuliahan beserta ruangannya. |
| Course | Course merupakan mata kuliah. |
| Lecturer | Lecturer merupakan dosen pengampu suatu course. |
| Constraint | Constraint merupakan jam-jam tertentu ketika seorang lecturer tidak dapat melakukan perkuliahan. |
| Preference | Preference  merupakan jam-jam tertentu ketika seorang lecturer ingin melakukan perkuliahan. |
| Capacity | Capacity merupakan daya tampung mahasiswa dan dosen pada suatu room. |

### Anggota Kelompok (K3)

  - Muthmainnah | 13515059
  - Luthfi Fadillah	| 13515072
  - Aya Aurora R | 13515098
  - Arfinda Ilmania | 13515137

