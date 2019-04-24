package Entity;

/**
 *
 * @author furkankykc
 */
public class Duyuru {
    
    private int id;
    private String baslik;
    private String icerik;

    public Duyuru() {
    }

    public Duyuru(String baslik, String icerik) {
        this.baslik = baslik;
        this.icerik = icerik;
    }

    public Duyuru(int id, String baslik, String icerik) {
        this.id = id;
        this.baslik = baslik;
        this.icerik = icerik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    @Override
    public String toString() {
        return "Duyuru{" + "id=" + id + ", baslik=" + baslik + ", icerik=" + icerik + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Duyuru other = (Duyuru) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}