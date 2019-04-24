/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BransDAO;
import Entity.Brans;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "bransController")
@SessionScoped
public class BransController implements Serializable {

    private List<Brans> bransList;
    private BransDAO bransDao;
    private Brans brans;

    public BransController() {
        this.bransList = new ArrayList<Brans>();
        this.bransDao = new BransDAO();
    }

    public List<Brans> getaList() {
        this.bransList = getaDao().list();
        return bransList;
    }

    public BransDAO getaDao() {
        if (this.bransDao == null) {
            this.bransDao = new BransDAO();
        }
        return bransDao;
    }

    public Brans getBrans() {
        if (this.brans == null) {
            this.brans = new Brans();
        }
        return brans;
    }

    public void setBrans(Brans brans) {
        this.brans = brans;
    }

    public String create() {

        this.getaDao().create(this.brans);
        clearForm();
        return "brans";
    }

    public String updateForm(Brans brans) {
        this.brans = brans;
        return "brans";
    }

    public void clearForm() {
        this.brans = new Brans();

    }

    public String update() {
        this.bransDao.update(this.brans);
        this.clearForm();
        return "brans";
    }

    public String delete(Brans brans) {
        this.brans = brans;
        return "confirm_delete";

    }

    public String delete() {
        this.brans = brans;
        this.getaDao().delete(brans.getId());
        clearForm();
        return "brans";

    }

}
