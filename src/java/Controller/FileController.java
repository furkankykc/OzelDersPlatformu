/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.FileDAO;
import Entity.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

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
    private Part part;

    private int page = 1;
    private int pageSize = 10;
    private int pageCount;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename;
            }
        }
        return "";

    }

    public void upload() {
        try (InputStream input = part.getInputStream()) {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext(); 
            String yol = "/resources/images/";
            String webContentRoot = ec.getRealPath(yol);
            Path a = new java.io.File(webContentRoot, part.getSubmittedFileName()).toPath();
            try {
                Files.copy(input, a);
            } catch (Exception ex) {
            }
            
            
           

            this.file = new File(part.getSubmittedFileName(), a.toAbsolutePath().toString(), part.getContentType());
            System.out.println(file);
            this.getaDao().create(this.file);
        } catch (Exception ex) {
            System.err.print(ex);
        }
    }

    public FileController() {
        this.fileList = new ArrayList<File>();
        this.fileDao = new FileDAO();
    }

    public List<File> getaList() {
        this.fileList = getaDao().list(page, pageSize);
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
