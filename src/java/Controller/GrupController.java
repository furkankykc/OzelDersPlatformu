/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GrupDAO;
import Entity.Grup;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "grupController")
@SessionScoped
public class GrupController implements Serializable {

    private List<Grup> grupList;
    private GrupDAO grupDao;
    private Grup grup;
    
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public GrupController() {
        this.grupList = new ArrayList<Grup>();
        this.grupDao = new GrupDAO();
    }

    public List<Grup> getaList() {
        this.grupList = getaDao().list(page,pageSize);
        return grupList;
    }

    public GrupDAO getaDao() {
        if (this.grupDao == null) {
            this.grupDao = new GrupDAO();
        }
        return grupDao;
    }

    public Grup getGrup() {
        if (this.grup == null) {
            this.grup = new Grup();
        }
        return grup;
    }

    public void setGrup(Grup grup) {
        this.grup = grup;
    }

    public String create() {

        this.getaDao().create(this.grup);
        clearForm();
        return "grup";
    }

    public String updateForm(Grup grup) {
        this.grup = grup;
        return "grup";
    }

    public void clearForm() {
        this.grup = new Grup();

    }

    public String update() {
        this.grupDao.update(this.grup);
        this.clearForm();
        return "grup";
    }

    public String delete(Grup grup) {
        this.grup = grup;
        return "confirm_delete";

    }

    public String delete() {
        this.grup = grup;
        this.getaDao().delete(grup);
        clearForm();
        return "grup";

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
