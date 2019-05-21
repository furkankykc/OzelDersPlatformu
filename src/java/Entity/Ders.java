package Entity;

/**
 *
 * @author furkankykc
 */
public class Ders {

    private int id;
    private String adi;
    private int ucret;
    private Brans brans;
    private User user;
    private File image;

    public Ders() {
    }

    public Ders(int ucret) {
        this.ucret = ucret;
    }

    public Ders(String adi, int ucret, Brans brans, File image, User user) {
        this.adi = adi;
        this.ucret = ucret;
        this.brans = brans;
        this.image = image;
        this.user = user;
    }

    public Ders(int id, String adi, int ucret, Brans brans, File image, User user) {
        this.id = id;
        this.adi = adi;
        this.ucret = ucret;
        this.brans = brans;
        this.image = image;
        this.user = user;
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
        return ucret;
    }

    public void setUcret(int ucret) {
        this.ucret = ucret;
    }

    public Brans getBrans() {
        return brans;
    }

    public void setBrans(Brans brans) {
        this.brans = brans;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Ders{" + "id=" + id + ", adi=" + adi + ", ucret=" + ucret + ", brans=" + brans + ", user=" + user + ", image=" + image + '}';
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
