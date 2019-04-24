
package Entity;

/**
 *
 * @author furkankykc
 */
public class Yorum{
    private int id;
    private User user;
    private String yorum;

    public Yorum() {
    }

    public Yorum(User user, String yorum) {
        this.user = user;
        this.yorum = yorum;
    }

    public Yorum(int id, User user, String yorum) {
        this.id = id;
        this.user = user;
        this.yorum = yorum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }

    @Override
    public String toString() {
        return "Yorum{" + "id=" + id + ", user=" + user + ", yorum=" + yorum + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
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
        final Yorum other = (Yorum) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
     
}