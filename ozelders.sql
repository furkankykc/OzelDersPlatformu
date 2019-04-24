DROP DATABASE IF EXISTS ozelders;
CREATE DATABASE IF NOT EXISTS ozelders;
USE ozelders;

CREATE TABLE IF NOT EXISTS brans (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  adi varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS ders (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  adi varchar(50) DEFAULT NULL,
  ucret int(11) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS duyuru (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  baslik varchar(50) DEFAULT NULL,
  icerik varchar(200) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS file (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  type varchar(50) DEFAULT NULL,
  adi varchar(50) DEFAULT NULL,
  path varchar(50) DEFAULT NULL
);


CREATE TABLE IF NOT EXISTS gun (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  adi varchar(50) DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS kategori (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  adi varchar(50) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS mesaj (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  gonderici_id int(11) DEFAULT NULL,
  alici_id int(11) DEFAULT NULL,
  title varchar(100) DEFAULT NULL,
  icerik varchar(1000) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS saat (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  saat varchar(2) DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS user (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  isim varchar(100) DEFAULT NULL,
  sehir varchar(250) DEFAULT NULL,
  email varchar(100) DEFAULT NULL UNIQUE,
  telefon varchar(16) DEFAULT NULL,
  egitim_duzeyi varchar(100) DEFAULT NULL,
  okul_durumu varchar(250) DEFAULT NULL,
  uyelik_tarihi date DEFAULT NULL,
  meslek varchar(100) DEFAULT NULL,
  diger varchar(1000) DEFAULT NULL,
  bakiye int(11) DEFAULT NULL,
  password varchar(500) DEFAULT NULL
  );

CREATE TABLE IF NOT EXISTS user_grup (
  user_id int(11) DEFAULT NULL,
  grup_id int(11) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS yetki (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `create` int(11) DEFAULT NULL,
  `delete` int(11) DEFAULT NULL,
  `update` int(11) DEFAULT NULL,
  `read` int(11) DEFAULT NULL,
  tablo_adi varchar(50) DEFAULT NULL
);
CREATE TABLE IF NOT EXISTS grup (
  id int(11)  AUTO_INCREMENT PRIMARY KEY,
  adi varchar(50) DEFAULT NULL,
  yetki_id int,
  FOREIGN KEY (yetki_id) REFERENCES yetki(id) ON DELETE SET NULL

);

CREATE TABLE IF NOT EXISTS yorum (
  id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id varchar(999) DEFAULT NULL,
  yorum varchar(999) DEFAULT NULL
);
