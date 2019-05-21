/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DersDAO;
import DAO.UserDAO;
import java.util.ArrayList;
import Entity.Ders;
import Entity.User;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "dersController")
@SessionScoped
public class DersController implements Serializable {

    private List<Ders> dersList;
    private DersDAO dersDao;
    private UserDAO userDao;
    private Ders ders;

    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public DersController() {
        this.dersList = new ArrayList<Ders>();
        this.dersDao = new DersDAO();
    }

    public List<Ders> getaList() {
        this.dersList = getaDao().list();
        return dersList;
    }

    public UserDAO getUserDao() {
        if (userDao == null) {
            userDao = new UserDAO();
        }
        return userDao;
    }

    public DersDAO getaDao() {
        if (this.dersDao == null) {
            this.dersDao = new DersDAO();
        }
        return dersDao;
    }

    public Ders getDers() {
        if (this.ders == null) {
            this.ders = new Ders();
        }
        return ders;
    }

    public void setDers(Ders ders) {
        this.ders = ders;
    }

    public String create() {

        this.getaDao().create(this.ders);
        clearForm();
        return "ders";
    }

    public String updateForm(Ders ders) {
        this.ders = ders;
        return "ders";
    }

    public void clearForm() {
        this.ders = new Ders();

    }

    public String update() {
        this.dersDao.update(this.ders);
        this.clearForm();
        return "ders";
    }

    public String delete(Ders ders) {
        this.ders = ders;
        return "confirm_delete";

    }

    public String delete() {
        this.ders = ders;
        this.getaDao().delete(ders.getId());
        clearForm();
        return "ders";

    }

    public boolean buy(Ders ders) {
        try {
            User currentUser = Utility.SessionUtils.getUser();
            int userBakiye = currentUser.getBakiye();
            int ucret = ders.getUcret();
            System.out.println("Bakiye : "+userBakiye);
            System.out.println("user : "+currentUser);
            int diff = userBakiye - ucret;
            if (diff >= 0) {
                currentUser.setBakiye(diff);
                getUserDao().update(currentUser);
                User owner = getUserDao().get(ders.getUser().getEmail());
                owner.setBakiye(owner.getBakiye() + ucret);
                getUserDao().update(owner);

                return true;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return false;
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
