package Entity;


/**
 *
 * @author furkankykc
 */
public class Saat {

    private int id;
    private String saat;

    public Saat() {
    }

    public Saat(String saat) {
        this.saat = saat;
    }

    public Saat(int id, String saat) {
        this.id = id;
        this.saat = saat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    @Override
    public String toString() {
        return "Saat{" + "id=" + id + ", saat=" + saat + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.id;
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
        final Saat other = (Saat) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    
    
}