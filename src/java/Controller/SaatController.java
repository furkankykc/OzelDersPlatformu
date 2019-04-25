/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.SaatDAO;
import Entity.Saat;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "saatController")
@SessionScoped
public class SaatController implements Serializable {

    private List<Saat> bransList;
    private SaatDAO bransDao;
    private Saat brans;
    
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    public SaatController() {
        this.bransList = new ArrayList<Saat>();
        this.bransDao = new SaatDAO();
    }

    public List<Saat> getaList() {
        this.bransList = getaDao().list(page,pageSize);
        return bransList;
    }

    public SaatDAO getaDao() {
        if (this.bransDao == null) {
            this.bransDao = new SaatDAO();
        }
        return bransDao;
    }

    public Saat getSaat() {
        if (this.brans == null) {
            this.brans = new Saat();
        }
        return brans;
    }

    public void setSaat(Saat brans) {
        this.brans = brans;
    }

    public String create() {

        this.getaDao().create(this.brans);
        clearForm();
        return "brans";
    }

    public String updateForm(Saat brans) {
        this.brans = brans;
        return "brans";
    }

    public void clearForm() {
        this.brans = new Saat();

    }

    public String update() {
        this.bransDao.update(this.brans);
        this.clearForm();
        return "brans";
    }

    public String delete(Saat brans) {
        this.brans = brans;
       
        return "confirm_delete";

    }

    public String delete() {
        this.brans = brans;
        this.getaDao().delete(brans.getId());
        clearForm();
        return "brans";

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
