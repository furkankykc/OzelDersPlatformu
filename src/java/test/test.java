/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template grup, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import DAO.*;
import Entity.*;
import java.util.ArrayList;

/**
 *
 * @author furkankykc
 */
public class test {

    public static boolean test_BransDAO() {
        BransDAO bransDao = new BransDAO();
        KategoriDAO kategoriDao = new KategoriDAO();
        Kategori test_kategori = kategoriDao.get(kategoriDao.create(new Kategori("test_kategori")));
        Brans brans = new Brans("test_name", test_kategori);
        int generated_id = bransDao.create(brans);
        brans.setId(generated_id);
        System.out.println(brans);
        if (generated_id != 0) {
            System.out.println("Brans create Success");
            Brans new_brans = bransDao.get(generated_id);
            if (new_brans.equals(brans)) {
                System.out.println("Brans read   Success");
                new_brans.setAdi("test_name2");
                bransDao.update(new_brans);
                Brans updated_brans = bransDao.get(new_brans.getId());
                if (updated_brans.getAdi().compareTo(new_brans.getAdi()) == 0) {
                    System.out.println("Brans update Success");
                    bransDao.delete(generated_id);
                    Brans deleted = bransDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("Brans delete Success");

                        return true;
                    } else {
                        System.out.println("Brans delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("Brans update Failed");
                    return false;
                }

            } else {
                System.out.println("Brans read    Failed");
                return false;
            }

        } else {
            System.out.println("Brans create Failed");
            return false;
        }
    }

    public static boolean test_DersDAO() {
        DersDAO dersDao = new DersDAO();
        BransDAO bransDao = new BransDAO();
        UserDAO userDao = new UserDAO();
        FileDAO fileDao = new FileDAO();
        KategoriDAO kategoriDao = new KategoriDAO();
        User test_user = userDao.get(userDao.create(new User("bilal", "1234")));
        File test_file = fileDao.get(fileDao.create(new File("test", "", "img")));
        Kategori test_kategori = kategoriDao.get(kategoriDao.create(new Kategori("test_kategori")));
        Brans test_brans = bransDao.get(bransDao.create(new Brans("test_brans", test_kategori)));

        Ders ders = new Ders("test_name", 12, test_brans, test_file, test_user);
        int generated_id = dersDao.create(ders);
        ders.setId(generated_id);
        System.out.println(ders);
        if (generated_id != 0) {
            System.out.println("Ders create Success");
            Ders new_ders = dersDao.get(generated_id);
            if (new_ders.equals(ders)) {
                System.out.println("Ders read   Success");
                new_ders.setAdi("new_name");
                new_ders.setUcret(111);
                dersDao.update(new_ders);
                Ders updated_ders = dersDao.get(new_ders.getId());
                if (updated_ders.getAdi().compareTo(new_ders.getAdi()) == 0
                        && updated_ders.getUcret() == new_ders.getUcret()) {
                    System.out.println("Ders update Success");
                    dersDao.delete(generated_id);
                    Ders deleted = dersDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("Ders delete Success");

                        return true;
                    } else {
                        System.out.println("Ders delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("Ders update Failed");
                    return false;
                }

            } else {
                System.out.println("Ders read    Failed");
                return false;
            }

        } else {
            System.out.println("Ders create Failed");
            return false;
        }
    }

    public static boolean test_DuyuruDAO() {
        DuyuruDAO duyuruDao = new DuyuruDAO();
        Duyuru duyuru = new Duyuru("test_baslik", "test_icerik");
        int generated_id = duyuruDao.create(duyuru);
        duyuru.setId(generated_id);
        System.out.println(duyuru);
        if (generated_id != 0) {
            System.out.println("Duyuru create Success");
            Duyuru new_duyuru = duyuruDao.get(generated_id);
            if (new_duyuru.equals(duyuru)) {
                System.out.println("Duyuru read   Success");
                new_duyuru.setBaslik("new_baslik");
                new_duyuru.setIcerik("new_icerik");
                duyuruDao.update(new_duyuru);
                Duyuru updated_duyuru = duyuruDao.get(new_duyuru.getId());
                if (updated_duyuru.getBaslik().compareTo(new_duyuru.getBaslik()) == 0
                        && updated_duyuru.getIcerik().compareTo(new_duyuru.getIcerik()) == 0) {
                    System.out.println("Duyuru update Success");
                    duyuruDao.delete(generated_id);
                    Duyuru deleted = duyuruDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("Duyuru delete Success");

                        return true;
                    } else {
                        System.out.println("Duyuru delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("Duyuru update Failed");
                    return false;
                }

            } else {
                System.out.println("Duyuru read    Failed");
                return false;
            }

        } else {
            System.out.println("Duyuru create Failed");
            return false;
        }
    }

    public static boolean test_FileDAO() {
        FileDAO fileDao = new FileDAO();
        File file = new File("test_adi", "test_path", "test_type");
        int generated_id = fileDao.create(file);
        file.setId(generated_id);
        System.out.println(file);
        if (generated_id != 0) {
            System.out.println("File create Success");
            File new_file = fileDao.get(generated_id);
            if (new_file.equals(file)) {
                System.out.println("File read   Success");
                new_file.setAdi("new_adi");
                new_file.setPath("new_path");
                new_file.setType("new_type");
                fileDao.update(new_file);
                File updated_file = fileDao.get(new_file.getId());
                if (updated_file.getAdi().compareTo(new_file.getAdi()) == 0
                        && updated_file.getPath().compareTo(new_file.getPath()) == 0
                        && updated_file.getType().compareTo(new_file.getType()) == 0) {
                    System.out.println("File update Success");
                    fileDao.delete(generated_id);
                    File deleted = fileDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("File delete Success");

                        return true;
                    } else {
                        System.out.println("File delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("File update Failed");
                    return false;
                }

            } else {
                System.out.println("File read    Failed");
                return false;
            }

        } else {
            System.out.println("File create Failed");
            return false;
        }
    }

    public static boolean test_GrupDAO() {
        GrupDAO grupDao = new GrupDAO();
        YetkiDAO yetkiDao = new YetkiDAO();
        Yetki test_yetki = yetkiDao.get(yetkiDao.create(new Yetki(true, true, true, true, "test_yetki")));
        Yetki new_yetki = yetkiDao.get(yetkiDao.create(new Yetki(false, false, false, false, "new_yetki")));
        Grup grup = new Grup("test_adi", test_yetki);
        int generated_id = grupDao.create(grup);
        grup.setId(generated_id);
        System.out.println(grup);
        if (generated_id != 0) {
            System.out.println("Grup create Success");
            Grup new_grup = grupDao.get(generated_id);
            if (new_grup.equals(grup)) {
                System.out.println("Grup read   Success");
                new_grup.setAdi("new_adi");
                new_grup.setYetki(new_yetki);
                grupDao.update(new_grup);
                Grup updated_grup = grupDao.get(new_grup.getId());
                if (updated_grup.getAdi().compareTo(new_grup.getAdi()) == 0
                        && updated_grup.getYetki().equals(new_grup.getYetki())) {
                    System.out.println("Grup update Success");
                    grupDao.delete(generated_id);
                    Grup deleted = grupDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("Grup delete Success");
                        yetkiDao.delete(test_yetki);
                        yetkiDao.delete(new_yetki);

                        return true;
                    } else {
                        System.out.println("Grup delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("Grup update Failed");
                    return false;
                }

            } else {
                System.out.println("Grup read    Failed");
                return false;
            }

        } else {
            System.out.println("Grup create Failed");
            return false;
        }
    }

    public static boolean test_MesajDAO() {
        MesajDAO mesajDao = new MesajDAO();
        UserDAO userDao = new UserDAO();
        User test_user = userDao.get(userDao.create(new User("bilal", "1234")));
        User new_user = userDao.get(userDao.create(new User("furkan", "1234")));
        Mesaj mesaj = new Mesaj(test_user, new_user, "test_title", "test_icerik");
        int generated_id = mesajDao.create(mesaj);
        mesaj.setId(generated_id);
        System.out.println(mesaj);
        if (generated_id != 0) {
            System.out.println("Mesaj create Success");
            Mesaj new_mesaj = mesajDao.get(generated_id);
            if (new_mesaj.equals(mesaj)) {
                System.out.println("Mesaj read   Success");
                new_mesaj.setTitle("new_title");
                new_mesaj.setIcerik("new_icerik");
                new_mesaj.setAlici(new_user);
                new_mesaj.setGonderici(test_user);
                mesajDao.update(new_mesaj);
                Mesaj updated_mesaj = mesajDao.get(new_mesaj.getId());
                if (updated_mesaj.getIcerik().compareTo(new_mesaj.getIcerik()) == 0
                        && updated_mesaj.getTitle().compareTo(new_mesaj.getTitle()) == 0
                        && updated_mesaj.getAlici().equals(new_mesaj.getAlici())
                        && updated_mesaj.getGonderici().equals(new_mesaj.getGonderici())) {
                    System.out.println("Mesaj update Success");
                    mesajDao.delete(generated_id);
                    Mesaj deleted = mesajDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("Mesaj delete Success");
                        userDao.delete(new_user);
                        userDao.delete(test_user);
                        return true;
                    } else {
                        System.out.println("Mesaj delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("Mesaj update Failed");
                    return false;
                }

            } else {
                System.out.println("Mesaj read    Failed");
                return false;
            }

        } else {
            System.out.println("Mesaj create Failed");
            return false;
        }
    }

    public static boolean test_KategoriDAO() {
        KategoriDAO kategoriDao = new KategoriDAO();
        Kategori kategori = new Kategori("test_adi");
        int generated_id = kategoriDao.create(kategori);
        kategori.setId(generated_id);
        System.out.println(kategori);
        if (generated_id != 0) {
            System.out.println("Kategori create Success");
            Kategori new_kategori = kategoriDao.get(generated_id);
            if (new_kategori.equals(kategori)) {
                System.out.println("Kategori read   Success");
                new_kategori.setAdi("new_adi");
                kategoriDao.update(new_kategori);
                Kategori updated_kategori = kategoriDao.get(new_kategori.getId());
                if (updated_kategori.getAdi().compareTo(new_kategori.getAdi()) == 0) {
                    System.out.println("Kategori update Success");
                    kategoriDao.delete(generated_id);
                    Kategori deleted = kategoriDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("Kategori delete Success");

                        return true;
                    } else {
                        System.out.println("Kategori delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("Kategori update Failed");
                    return false;
                }

            } else {
                System.out.println("Kategori read    Failed");
                return false;
            }

        } else {
            System.out.println("Kategori create Failed");
            return false;
        }
    }

    public static boolean test_SaatDAO() {
        SaatDAO saatDao = new SaatDAO();
        Saat saat = new Saat("12");
        int generated_id = saatDao.create(saat);
        saat.setId(generated_id);
        System.out.println(saat);
        if (generated_id != 0) {
            System.out.println("Saat create Success");
            Saat new_saat = saatDao.get(generated_id);
            if (new_saat.equals(saat)) {
                System.out.println("Saat read   Success");
                new_saat.setSaat("14");
                saatDao.update(new_saat);
                Saat updated_saat = saatDao.get(new_saat.getId());
                if (updated_saat.getSaat().compareTo(new_saat.getSaat()) == 0) {
                    System.out.println("Saat update Success");
                    saatDao.delete(generated_id);
                    Saat deleted = saatDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("Saat delete Success");

                        return true;
                    } else {
                        System.out.println("Saat delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("Saat update Failed");
                    return false;
                }

            } else {
                System.out.println("Saat read    Failed");
                return false;
            }

        } else {
            System.out.println("Saat create Failed");
            return false;
        }
    }

    public static boolean test_YetkiDAO() {
        YetkiDAO yetkiDao = new YetkiDAO();
        Yetki yetki = new Yetki(true, true, true, true, "test_tablo");
        int generated_id = yetkiDao.create(yetki);
        yetki.setId(generated_id);
        System.out.println(yetki);
        if (generated_id != 0) {
            System.out.println("Yetki create Success");
            Yetki new_yetki = yetkiDao.get(generated_id);
            if (new_yetki.equals(yetki)) {
                System.out.println("Yetki read   Success");
                new_yetki.setCreate(false);
                new_yetki.setRead(false);
                new_yetki.setUpdate(false);
                new_yetki.setDelete(false);
                new_yetki.setTabloAdi("new_tablo");
                yetkiDao.update(new_yetki);
                Yetki updated_yetki = yetkiDao.get(new_yetki.getId());
                if (updated_yetki.getTabloAdi().compareTo(new_yetki.getTabloAdi()) == 0
                        && updated_yetki.isCreate() == new_yetki.isCreate()
                        && updated_yetki.isRead() == new_yetki.isRead()
                        && updated_yetki.isUpdate() == new_yetki.isUpdate()
                        && updated_yetki.isDelete() == new_yetki.isDelete()) {
                    System.out.println("Yetki update Success");
                    yetkiDao.delete(generated_id);
                    Yetki deleted = yetkiDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("Yetki delete Success");

                        return true;
                    } else {
                        System.out.println("Yetki delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("Yetki update Failed");
                    return false;
                }

            } else {
                System.out.println("Yetki read    Failed");
                return false;
            }

        } else {
            System.out.println("Yetki create Failed");
            return false;
        }
    }

    public static boolean test_YorumDAO() {
        YorumDAO yorumDao = new YorumDAO();
        UserDAO userDao = new UserDAO();
        User test_user = userDao.get(userDao.create(new User("furkan", "1234")));
        User new_user = userDao.get(userDao.create(new User("bilal", "1234")));
        Yorum yorum = new Yorum(test_user, "test_yorum");
        int generated_id = yorumDao.create(yorum);
        yorum.setId(generated_id);
        System.out.println(yorum);
        if (generated_id != 0) {
            System.out.println("Yorum create Success");
            Yorum new_yorum = yorumDao.get(generated_id);
            if (new_yorum.equals(yorum)) {
                System.out.println("Yorum read   Success");
                new_yorum.setYorum("new_yorum");
                new_yorum.setUser(new_user);
                yorumDao.update(new_yorum);
                Yorum updated_yorum = yorumDao.get(new_yorum.getId());
                if (updated_yorum.getYorum().compareTo(new_yorum.getYorum()) == 0
                        && updated_yorum.getUser().equals(new_yorum.getUser())) {
                    System.out.println("Yorum update Success");
                    yorumDao.delete(generated_id);
                    Yorum deleted = yorumDao.get(generated_id);
                    if (deleted == null) {
                        System.out.println("Yorum delete Success");
                        userDao.delete(new_user);
                        userDao.delete(test_user);
                        return true;
                    } else {
                        System.out.println("Yorum delete Failed");

                        return false;
                    }
                } else {
                    System.out.println("Yorum update Failed");
                    return false;
                }

            } else {
                System.out.println("Yorum read    Failed");
                return false;
            }

        } else {
            System.out.println("Yorum create Failed");
            return false;
        }
    }

    public static void initializeDB() {
        UserDAO userDao = new UserDAO();
        GrupDAO grupDao = new GrupDAO();
        YetkiDAO yetkiDao = new YetkiDAO();
        Yetki yetki_admin = yetkiDao.get(yetkiDao.create(new Yetki(true, true, true, true, "admin")));
        Yetki yetki_user = yetkiDao.get(yetkiDao.create(new Yetki(false, false, false, false, "user")));

        Grup grup_admin = grupDao.get(grupDao.create(new Grup("admin", yetki_admin)));
        Grup grup_user = grupDao.get(grupDao.create(new Grup("user", yetki_user)));
        User admin = new User("root@mail.com", "1234");
        admin.getGrup().add(grup_admin);
        User user = new User("user@mail.com", "1234");

        user.getGrup().add(grup_user);
        userDao.create(admin);
        userDao.create(user);

    }

    public static void main(String[] args) {

//        UserDAO udao = new UserDAO();
//        User a = new User();
//        ArrayList<Grup> arrayList = new ArrayList<Grup>();
//        arrayList.add(new Grup(1,"asdasd"));
//        arrayList.add(new Grup(2,"asdasd"));
//        a.setGrup(arrayList);
//        a.setIsim("ff");
////        udao.create(a);
//        String str = "";
//        if (test_BransDAO()) {
//            str += "TEST SUCCESSFULL FOR BransDAO\n";
//        }
//        if (test_DersDAO()) {
//            str += "TEST SUCCESSFULL FOR DersDAO\n";
//        }
//        if (test_DuyuruDAO()) {
//            str += "TEST SUCCESSFULL FOR DuyuruDAO\n";
//        }
//        if (test_FileDAO()) {
//            str += "TEST SUCCESSFULL FOR FileDAO\n";
//        }
//        if (test_GrupDAO()) {
//            str += "TEST SUCCESSFULL FOR GrupDAO\n";
//        }
//        if (test_KategoriDAO()) {
//            str += "TEST SUCCESSFULL FOR KategoriDAO\n";
//        }
//        if (test_MesajDAO()) {
//            str += "TEST SUCCESSFULL FOR MesajDAO\n";
//        }
//        if (test_SaatDAO()) {
//            str += "TEST SUCCESSFULL FOR SaatDAO\n";
//        }
//        if (test_YetkiDAO()) {
//            str += "TEST SUCCESSFULL FOR YetkiDAO\n";
//        }
//        if (test_YorumDAO()) {
//            str += "TEST SUCCESSFULL FOR YorumDAO\n";
//        }
//        System.out.println(str);
//        initializeDB();
        User u = new UserDAO().get("root");
        u.setEmail("root@mail.com");
        System.out.println(u.getGrup());
        new UserDAO().update(u);
    }

}
