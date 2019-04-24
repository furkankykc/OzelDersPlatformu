package Entity;

/**
 *
 * @author furkankykc
 */
public class File {
    
    private int id;
    private String adi;
    private String path;
    private String type;

    public File() {
    }

    public File(String adi, String path, String type) {
        this.adi = adi;
        this.path = path;
        this.type = type;
    }

    public File(int id, String adi, String path, String type) {
        this.id = id;
        this.adi = adi;
        this.path = path;
        this.type = type;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "File{" + "id=" + id + ", adi=" + adi + ", path=" + path + ", type=" + type + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final File other = (File) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
    
}