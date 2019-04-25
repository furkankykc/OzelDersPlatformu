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
    
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public MesajController() {
        this.mesajList = new ArrayList<Mesaj>();
        this.mesajDao = new MesajDAO();
    }

    public List<Mesaj> getaList() {
        this.mesajList = getaDao().list(page,pageSize);
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
