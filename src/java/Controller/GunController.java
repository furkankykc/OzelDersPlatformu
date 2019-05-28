/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GunDAO;
import Entity.Gun;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "gunController")
@SessionScoped
public class GunController implements Serializable {

    private List<Gun> gunList;
    private GunDAO gunDao;
    private Gun gun;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    
    public GunController() {
        this.gunList = new ArrayList<Gun>();
        this.gunDao = new GunDAO();
    }

 public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Gun> search() {
        ArrayList<Gun> resultList = new ArrayList<Gun>();
        for (Gun gun : this.gunList) {
            if (gun.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(gun);
            }
        }

        return resultList;
    }

    public List<Gun> getaList() {
        this.gunList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.gunList = this.search();
        }
        System.out.println("|||"+this.gunList);
        return gunList;
    }
    public GunDAO getaDao() {
        if (this.gunDao == null) {
            this.gunDao = new GunDAO();
        }
        return gunDao;
    }

    public Gun getGun() {
        if (this.gun == null) {
            this.gun = new Gun();
        }
        return gun;
    }

    public void setGun(Gun gun) {
        this.gun = gun;
    }

    public String create() {

        this.getaDao().create(this.gun);
        clearForm();
        return "gun";
    }

    public String updateForm(Gun gun) {
        this.gun = gun;
        return "gun";
    }

    public void clearForm() {
        this.gun = new Gun();

    }

    public String update() {
        this.gunDao.update(this.gun);
        this.clearForm();
        return "gun";
    }

    public String delete(Gun gun) {
        this.gun = gun;
       
        return "confirm_delete";

    }

    public String delete() {
        this.gun = gun;
        this.getaDao().delete(gun.getId());
        clearForm();
        return "gun";

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
