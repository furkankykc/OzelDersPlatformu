/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.DuyuruDAO;
import Entity.Duyuru;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "duyuruController")
@SessionScoped
public class DuyuruController implements Serializable {

    private List<Duyuru> duyuruList;
    private DuyuruDAO duyuruDao;
    private Duyuru duyuru;

    public DuyuruController() {
        this.duyuruList = new ArrayList<Duyuru>();
        this.duyuruDao = new DuyuruDAO();
    }

    public List<Duyuru> getaList() {
        this.duyuruList = getaDao().list();
        return duyuruList;
    }

    public DuyuruDAO getaDao() {
        if (this.duyuruDao == null) {
            this.duyuruDao = new DuyuruDAO();
        }
        return duyuruDao;
    }

    public Duyuru getDuyuru() {
        if (this.duyuru == null) {
            this.duyuru = new Duyuru();
        }
        return duyuru;
    }

    public void setDuyuru(Duyuru duyuru) {
        this.duyuru = duyuru;
    }

    public String create() {

        this.getaDao().create(this.duyuru);
        clearForm();
        return "duyuru";
    }

    public String updateForm(Duyuru duyuru) {
        this.duyuru = duyuru;
        return "duyuru";
    }

    public void clearForm() {
        this.duyuru = new Duyuru();

    }

    public String update() {
        this.duyuruDao.update(this.duyuru);
        this.clearForm();
        return "duyuru";
    }

    public String delete(Duyuru duyuru) {
        this.duyuru = duyuru;
        return "confirm_delete";

    }

    public String delete() {
        this.duyuru = duyuru;
        this.getaDao().delete(duyuru.getId());
        clearForm();
        return "duyuru";

    }

}
