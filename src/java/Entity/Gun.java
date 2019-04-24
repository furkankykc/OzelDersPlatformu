package Entity;

/**
 *
 * @author furkankykc
 */
public class Gun{
    private int id;
    private String adi;

    public Gun() {
    }

    public Gun(String adi) {
        this.adi = adi;
    }

    public Gun(int id, String adi) {
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
        return "Gun{" + "id=" + id + ", adi=" + adi + '}';
    }

    

}