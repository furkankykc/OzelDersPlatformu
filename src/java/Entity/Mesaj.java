package Entity;

/**
 *
 * @author furkankykc
 */
public class Mesaj{
    
    private int id;
    private User gonderici;
    private User alici;
    private String title;
    private String icerik;

    public Mesaj() {
    }

    public Mesaj(User gonderici, User alici, String title, String icerik) {
        this.gonderici = gonderici;
        this.alici = alici;
        this.title = title;
        this.icerik = icerik;
    }

    public Mesaj(int id, User gonderici, User alici, String title, String icerik) {
        this.id = id;
        this.gonderici = gonderici;
        this.alici = alici;
        this.title = title;
        this.icerik = icerik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getGonderici() {
        return gonderici;
    }

    public void setGonderici(User gonderici) {
        this.gonderici = gonderici;
    }

    public User getAlici() {
        return alici;
    }

    public void setAlici(User alici) {
        this.alici = alici;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    @Override
    public String toString() {
        return "Mesaj{" + "id=" + id + ", gonderici=" + gonderici + ", alici=" + alici + ", title=" + title + ", icerik=" + icerik + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.id;
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
        final Mesaj other = (Mesaj) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}