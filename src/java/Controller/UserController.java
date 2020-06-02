/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import Entity.Grup;
import Entity.User;
import Entity.Yetki;
import Utility.SessionUtils;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "userController")
@SessionScoped
public class UserController implements Serializable {

    private List<User> userList;
    private UserDAO userDao;
    private User user;
    private String passControll;
    private String message;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public UserController() {
        this.userList = new ArrayList<User>();
        this.userDao = new UserDAO();
    }

 public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<User> search() {
        ArrayList<User> resultList = new ArrayList<User>();
        for (User user : this.userList) {
            if (user.getIsim().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(user);
            }
        }

        return resultList;
    }

    public List<User> getaList() {
        this.userList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.userList = this.search();
        }
        System.out.println("|||"+this.userList);
        return userList;
    }
    public UserDAO getaDao() {
        if (this.userDao == null) {
            this.userDao = new UserDAO();
        }
        return userDao;
    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String create() {
        if(this.user.getImage()!=null)
            this.getaDao().createWithImage(this.user);
        this.getaDao().create(this.user);
        clearForm();
        return "user";
    }

    public String updateForm(User user) {
        this.user = user;
        return "user";
    }

    public void clearForm() {
        this.user = new User();

    }

    public String update() {
        this.userDao.update(this.user);
        this.clearForm();
        return "user";
    }

    public String delete(User user) {
        this.user = user;

        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(user.getId());
        clearForm();
        return "user";

    }

    public String getPassConroll() {
        return passControll;
    }

    public String getMessage() {
        return message;
    }

    public void setPassConroll(String passConroll) {
        this.passControll = passConroll;
    }

    public Boolean isLoggedin() {

        return SessionUtils.isLoggedin();
    }

    public String getUserId() {
        HttpSession session = SessionUtils.getSession();

        return session.getAttribute("userid").toString();
    }

    public Yetki getYetki() {

        Yetki yetki = new Yetki(false, false, false, false, "");
        if (Utility.SessionUtils.getUser().getGrup() == null) {
            return yetki;
        }
        for (Grup grup : Utility.SessionUtils.getUser().getGrup()) {
            Yetki yetkiler = grup.getYetki();
            System.out.println(yetkiler);
            if (yetkiler.isCreate()) {
                yetki.setCreate(true);
            }
            if (yetkiler.isRead()) {
                yetki.setRead(true);
            }
            if (yetkiler.isUpdate()) {
                yetki.setUpdate(true);
            }
            if (yetkiler.isDelete()) {
                yetki.setDelete(true);
            }

        }
//        System.out.println(yetki);
        return yetki;
    }

    public String panel() {
        return "admin/index.xhtml";
    }

    public String site() {
        return "/index.xhtml";
    }

    public String login() {
        if(getaDao().list().isEmpty())
            test.test.initializeDB();
        User user = this.getaDao().get(this.user.getEmail());
        message = "";
        if (user != null) {
            if (user.getPassword().compareTo(this.user.getPassword()) == 0) {
                Utility.SessionUtils.setUser(user);
                return "/index.xhtml";
                //giris yaptınız
  
            } else {

                message = "Şifren yanlış";
                //sifre yanlis
                return "";
            }
        } else {

            message = "Kullanıcı adın veya şifren yanlış";
            //user adi sifre yanlis
        }

        return "";
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        this.user = null;
        return "/index.xhtml";

    }

    public Boolean getType() {

        return SessionUtils.isAdmin();
    }

    public Boolean getSuperAdmin() {

        return SessionUtils.isSuperAdmin();
    }

    public String register() {
        User user = this.getaDao().get(this.user.getIsim());
        message = "";
        if (this.user.getIsim().compareTo("") != 0) {

            if (this.user.getPassword().compareTo(this.passControll) == 0) {
                if (user == null) {
                    if (this.user.getPassword().compareTo("") != 0) {

                        this.getaDao().create(this.user);

                        return "login";

                    } else {
                        message = "Şifre kısmı boş olamaz";
                    }
                } else {

                    message = "Bu kullanıcı adı daha önceden alınmış";
                    // sifreler tutmuyor
                }
            } else {
                message = "Şifrelerin birbiriyle aynı değil";
                //bu user adi daha önce alınmıs
            }
        } else {
            message = "Kullanici adi boş olamaz";
        }
        return "";
    }
    
    public void next() {
        if (page < pageCount) {
            this.page++;
        }
    }

    public void previous() {
        if (page > 1) {
            this.page--;
        }
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        try {
            this.pageCount = (int) Math.ceil(this.getaDao().count() / (double) this.pageSize);
        } catch (Exception e) {
            return 1;
        }

        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
