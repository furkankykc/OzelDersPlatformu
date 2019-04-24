package Entity;

/**
 *
 * @author furkankykc
 */
public class Grup {

    private int id;
    private String adi;
    private Yetki yetki;

    public Grup() {
    }

    public Grup(String adi,Yetki yetki) {
        this.adi = adi;
        this.yetki = yetki;
    }

    public Grup(int id, String adi,Yetki yetki) {
        this.id = id;
        this.adi = adi;
        this.yetki = yetki;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public Yetki getYetki() {
        return yetki;
    }

    public void setYetki(Yetki yetki) {
        this.yetki = yetki;
    }

    @Override
    public String toString() {
        return "Grup{" + "id=" + id + ", adi=" + adi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
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
        final Grup other = (Grup) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}
    