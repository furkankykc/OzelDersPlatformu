/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.YetkiDAO;
import Entity.Yetki;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "yetkiController")
@SessionScoped
public class YetkiController implements Serializable {

    private List<Yetki> yetkiList;
    private YetkiDAO yetkiDao;
    private Yetki yetki;

    public YetkiController() {
        this.yetkiList = new ArrayList<Yetki>();
        this.yetkiDao = new YetkiDAO();
    }

    public List<Yetki> getaList() {
        this.yetkiList = getaDao().list();
        return yetkiList;
    }

    public YetkiDAO getaDao() {
        if (this.yetkiDao == null) {
            this.yetkiDao = new YetkiDAO();
        }
        return yetkiDao;
    }

    public Yetki getYetki() {
        if (this.yetki == null) {
            this.yetki = new Yetki();
        }
        return yetki;
    }

    public void setYetki(Yetki yetki) {
        this.yetki = yetki;
    }

    public String create() {

        this.getaDao().create(this.yetki);
        clearForm();
        return "yetki";
    }

    public String updateForm(Yetki yetki) {
        this.yetki = yetki;
        return "yetki";
    }

    public void clearForm() {
        this.yetki = new Yetki();

    }

    public String update() {
        this.yetkiDao.update(this.yetki);
        this.clearForm();
        return "yetki";
    }

    public String delete(Yetki yetki) {
        this.yetki = yetki;
        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(yetki.getId());
        clearForm();
        return "yetki";
    }

}
