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

    private List<Saat> saatList;
    private SaatDAO saatDao;
    private Saat saat;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    public SaatController() {
        this.saatList = new ArrayList<Saat>();
        this.saatDao = new SaatDAO();
    }

 public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Saat> search() {
        ArrayList<Saat> resultList = new ArrayList<Saat>();
        for (Saat saat : this.saatList) {
            if (saat.getSaat().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(saat);
            }
        }

        return resultList;
    }

    public List<Saat> getaList() {
        this.saatList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.saatList = this.search();
        }
        System.out.println("|||"+this.saatList);
        return saatList;
    }
    public SaatDAO getaDao() {
        if (this.saatDao == null) {
            this.saatDao = new SaatDAO();
        }
        return saatDao;
    }

    public Saat getSaat() {
        if (this.saat == null) {
            this.saat = new Saat();
        }
        return saat;
    }

    public void setSaat(Saat saat) {
        this.saat = saat;
    }

    public String create() {

        this.getaDao().create(this.saat);
        clearForm();
        return "saat";
    }

    public String updateForm(Saat saat) {
        this.saat = saat;
        return "saat";
    }

    public void clearForm() {
        this.saat = new Saat();

    }

    public String update() {
        this.saatDao.update(this.saat);
        this.clearForm();
        return "saat";
    }

    public String delete(Saat saat) {
        this.saat = saat;
       
        return "confirm_delete";

    }

    public String delete() {
        this.saat = saat;
        this.getaDao().delete(saat.getId());
        clearForm();
        return "saat";

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
