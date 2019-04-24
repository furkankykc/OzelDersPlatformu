/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author furkankykc
 */
public class Brans {
    private int id;
    private String adi;

    public Brans() {
    }

    public Brans(int brans_id, String adi) {
        this.id = brans_id;
        this.adi = adi;
    }

    public Brans(String adi) {
        this.adi = adi;
    }

    public int getId() {
        return id;
    }

    public void setId(int brans_id) {
        this.id = brans_id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    @Override
    public String toString() {
        return "Brans{" + "brans_id=" + id + ", adi=" + adi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
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
        final Brans other = (Brans) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
