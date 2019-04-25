/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FileDAO;
import Entity.File;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "fileController")
@SessionScoped
public class FileController implements Serializable {

    private List<File> fileList;
    private FileDAO fileDao;
    private File file;

    private int page = 1;
    private int pageSize = 10;
    private int pageCount;
    public FileController() {
        this.fileList = new ArrayList<File>();
        this.fileDao = new FileDAO();
    }

    public List<File> getaList() {
        this.fileList = getaDao().list(page,pageSize);
        return fileList;
    }

    public FileDAO getaDao() {
        if (this.fileDao == null) {
            this.fileDao = new FileDAO();
        }
        return fileDao;
    }

    public File getFile() {
        if (this.file == null) {
            this.file = new File();
        }
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String create() {

        this.getaDao().create(this.file);
        clearForm();
        return "file";
    }

    public String updateForm(File file) {
        this.file = file;
        return "file";
    }

    public void clearForm() {
        this.file = new File();

    }

    public String update() {
        this.fileDao.update(this.file);
        this.clearForm();
        return "file";
    }

    public String delete(File file) {
        this.file = file;
        return "confirm_delete";


    }
    public String delete() {
        this.file = file;
        this.getaDao().delete(file.getId());
        clearForm();
        return "file";

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
