/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author furkankykc
 */
public class User {

    private int id;
    private int bakiye;
    private String telefon;
    private String isim;
    private String sehir;
    private String email;
    private String egitimDuzeyi;
    private String okulDurumu;
    private Date uyelikTarihi;
    private String meslek;
    private String diger;
    private String password;
    private List<Grup> grup;

    public User() {
        grup = new ArrayList<>();
    }

    public User(String isim, String password) {
        this.isim = isim;
        this.email = isim;
        this.password = password;
        this.grup = new ArrayList<>();
    }

    public User(int bakiye, String telefon, String isim, String sehir, String email, String egitimDuzeyi, String okulDurumu, Date uyelikTarihi, String meslek, String diger, String password, List<Grup> grup) {
        this.bakiye = bakiye;
        this.telefon = telefon;
        this.isim = isim;
        this.sehir = sehir;
        this.email = email;
        this.egitimDuzeyi = egitimDuzeyi;
        this.okulDurumu = okulDurumu;
        this.uyelikTarihi = uyelikTarihi;
        this.meslek = meslek;
        this.diger = diger;
        this.password = password;
        if (grup != null) {
            this.grup = grup;
        } else {
            this.grup = new ArrayList<>();
        }
    }

    public User(int id, int bakiye, String telefon, String isim, String sehir, String email, String egitimDuzeyi, String okulDurumu, Date uyelikTarihi, String meslek, String diger, String password, List<Grup> grup) {
        this.id = id;
        this.bakiye = bakiye;
        this.telefon = telefon;
        this.isim = isim;
        this.sehir = sehir;
        this.email = email;
        this.egitimDuzeyi = egitimDuzeyi;
        this.okulDurumu = okulDurumu;
        this.uyelikTarihi = uyelikTarihi;
        this.meslek = meslek;
        this.diger = diger;
        this.password = password;
        if (grup != null) {
            this.grup = grup;
        } else {
            this.grup = new ArrayList<>();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getSehir() {
        return sehir;
    }

    public void setSehir(String sehir) {
        this.sehir = sehir;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEgitimDuzeyi() {
        return egitimDuzeyi;
    }

    public void setEgitimDuzeyi(String egitimDuzeyi) {
        this.egitimDuzeyi = egitimDuzeyi;
    }

    public String getOkulDurumu() {
        return okulDurumu;
    }

    public void setOkulDurumu(String okulDurumu) {
        this.okulDurumu = okulDurumu;
    }

    public Date getUyelikTarihi() {
        return uyelikTarihi;
    }

    public void setUyelikTarihi(Date uyelikTarihi) {
        this.uyelikTarihi = uyelikTarihi;
    }

    public String getMeslek() {
        return meslek;
    }

    public void setMeslek(String meslek) {
        this.meslek = meslek;
    }

    public String getDiger() {
        return diger;
    }

    public void setDiger(String diger) {
        this.diger = diger;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Grup> getGrup() {
        return grup;
    }

    public void setGrup(List<Grup> grup) {
        this.grup = grup;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", bakiye=" + bakiye + ", telefon=" + telefon + ", isim=" + isim + ", sehir=" + sehir + ", email=" + email + ", egitimDuzeyi=" + egitimDuzeyi + ", okulDurumu=" + okulDurumu + ", uyelikTarihi=" + uyelikTarihi + ", meslek=" + meslek + ", diger=" + diger + ", password=" + password + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
