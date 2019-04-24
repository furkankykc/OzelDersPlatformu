package Entity;

/**
 *
 * @author furkankykc
 */
public class Ders {

    private int id;
    private String adi;
    private int Ucret;

    public Ders() {
    }

    public Ders(String adi, int Ucret) {
        this.adi = adi;
        this.Ucret = Ucret;
    }

    public Ders(int id, String adi, int Ucret) {
        this.id = id;
        this.adi = adi;
        this.Ucret = Ucret;
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

    public int getUcret() {
        return Ucret;
    }

    public void setUcret(int Ucret) {
        this.Ucret = Ucret;
    }

    @Override
    public String toString() {
        return "Ders{" + "id=" + id + ", adi=" + adi + ", Ucret=" + Ucret + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final Ders other = (Ders) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
