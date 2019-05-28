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
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    public DuyuruController() {
        this.duyuruList = new ArrayList<Duyuru>();
        this.duyuruDao = new DuyuruDAO();
    }

     public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Duyuru> search() {
        ArrayList<Duyuru> resultList = new ArrayList<Duyuru>();
        for (Duyuru duyuru : this.duyuruList) {
            if (duyuru.getBaslik().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(duyuru);
            }
        }

        return resultList;
    }

    public List<Duyuru> getaList() {
        this.duyuruList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.duyuruList = this.search();
        }
        System.out.println("|||"+this.duyuruList);
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
