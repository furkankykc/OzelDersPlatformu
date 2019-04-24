package Entity;

/**
 *
 * @author furkankykc
 */
public class Yetki{
    
    private int id;
    private boolean create;
    private boolean read;
    private boolean update;
    private boolean delete;
    private String tabloAdi;

    public Yetki() {
    }

    public Yetki(boolean create, boolean read, boolean update, boolean delete, String tabloAdi) {
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
        this.tabloAdi = tabloAdi;
    }

    public Yetki(int id, boolean create, boolean read, boolean update, boolean delete, String tabloAdi) {
        this.id = id;
        this.create = create;
        this.read = read;
        this.update = update;
        this.delete = delete;
        this.tabloAdi = tabloAdi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public String getTabloAdi() {
        return tabloAdi;
    }

    public void setTabloAdi(String tabloAdi) {
        this.tabloAdi = tabloAdi;
    }

    @Override
    public String toString() {
        return "Yetki{" + "id=" + id + ", create=" + create + ", read=" + read + ", update=" + update + ", delete=" + delete + ", tabloAdi=" + tabloAdi + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Yetki other = (Yetki) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}