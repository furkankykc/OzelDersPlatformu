package Entity;

/**
 *
 * @author furkankykc
 */
public class Kategori{

    private int id;
    private String adi;

    public Kategori() {
    }

    public Kategori(String adi) {
        this.adi = adi;
    }

    public Kategori(int id, String adi) {
        this.id = id;
        this.adi = adi;
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

    @Override
    public String toString() {
        return "Kategori{" + "id=" + id + ", adi=" + adi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.id;
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
        final Kategori other = (Kategori) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}