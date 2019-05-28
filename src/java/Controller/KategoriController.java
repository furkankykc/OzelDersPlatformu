/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.KategoriDAO;
import Entity.Kategori;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "kategoriController")
@SessionScoped
public class KategoriController implements Serializable {

    private List<Kategori> kategoriList;
    private KategoriDAO kategoriDao;
    private Kategori kategori;
    private String display = "";
    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    
    public KategoriController() {
        this.kategoriList = new ArrayList<Kategori>();
        this.kategoriDao = new KategoriDAO();
    }

 public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    
    public ArrayList<Kategori> search() {
        ArrayList<Kategori> resultList = new ArrayList<Kategori>();
        for (Kategori kategori : this.kategoriList) {
            if (kategori.getAdi().toLowerCase().startsWith(display.toLowerCase())) {
                resultList.add(kategori);
            }
        }

        return resultList;
    }

    public List<Kategori> getaList() {
        this.kategoriList = getaDao().list(page, pageSize);
        if (display != "" || display != null) {
            this.kategoriList = this.search();
        }
        System.out.println("|||"+this.kategoriList);
        return kategoriList;
    }   

    public KategoriDAO getaDao() {
        if (this.kategoriDao == null) {
            this.kategoriDao = new KategoriDAO();
        }
        return kategoriDao;
    }

    public Kategori getKategori() {
        if (this.kategori == null) {
            this.kategori = new Kategori();
        }
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String create() {

        this.getaDao().create(this.kategori);
        clearForm();
        return "kategori";
    }

    public String updateForm(Kategori kategori) {
        this.kategori = kategori;
        return "kategori";
    }

    public void clearForm() {
        this.kategori = new Kategori();

    }

    public String update() {
        this.kategoriDao.update(this.kategori);
        this.clearForm();
        return "kategori";
    }

    public String delete(Kategori kategori) {
        this.kategori = kategori;
        return "confirm_delete";

    }

    public String delete() {
        this.kategori = kategori;
        this.getaDao().delete(kategori.getId());
        clearForm();
        return "kategori";

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
