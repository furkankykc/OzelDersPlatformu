/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.MesajDAO;
import Entity.Mesaj;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "mesajController")
@SessionScoped
public class MesajController implements Serializable {

    private List<Mesaj> mesajList;
    private MesajDAO mesajDao;
    private Mesaj mesaj;

    public MesajController() {
        this.mesajList = new ArrayList<Mesaj>();
        this.mesajDao = new MesajDAO();
    }

    public List<Mesaj> getaList() {
        this.mesajList = getaDao().list();
        return mesajList;
    }

    public MesajDAO getaDao() {
        if (this.mesajDao == null) {
            this.mesajDao = new MesajDAO();
        }
        return mesajDao;
    }

    public Mesaj getMesaj() {
        if (this.mesaj == null) {
            this.mesaj = new Mesaj();
        }
        return mesaj;
    }

    public void setMesaj(Mesaj mesaj) {
        this.mesaj = mesaj;
    }

    public String create() {
        this.getaDao().create(this.mesaj);
        clearForm();
        return "mesaj";
    }

    public String updateForm(Mesaj mesaj) {
        this.mesaj = mesaj;
        return "mesaj";
    }

    public void clearForm() {
        this.mesaj = new Mesaj();

    }

    public String update() {
        this.mesajDao.update(this.mesaj);
        this.clearForm();
        return "mesaj";
    }

    public String delete(Mesaj mesaj) {
        this.mesaj = mesaj;
        return "confirm_delete";

    }

    public String delete() {
        this.mesaj = mesaj;
        this.getaDao().delete(mesaj.getId());
        clearForm();
        return "mesaj";

    }

}
