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

    public GunController() {
        this.gunList = new ArrayList<Gun>();
        this.gunDao = new GunDAO();
    }

    public List<Gun> getaList() {
        this.gunList = getaDao().list();
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

}
