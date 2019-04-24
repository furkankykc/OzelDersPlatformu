/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.YorumDAO;
import Entity.Yorum;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author furkankykc
 */
@ManagedBean(name = "yorumController")
@SessionScoped
public class YorumController implements Serializable {

    private List<Yorum> yorumList;
    private YorumDAO yorumDao;
    private Yorum yorum;

    public YorumController() {
        this.yorumList = new ArrayList<Yorum>();
        this.yorumDao = new YorumDAO();
    }

    public List<Yorum> getaList() {
        this.yorumList = getaDao().list();
        return yorumList;
    }

    public YorumDAO getaDao() {
        if (this.yorumDao == null) {
            this.yorumDao = new YorumDAO();
        }
        return yorumDao;
    }

    public Yorum getYorum() {
        if (this.yorum == null) {
            this.yorum = new Yorum();
        }
        return yorum;
    }

    public void setYorum(Yorum yorum) {
        this.yorum = yorum;
    }

    public String create() {

        this.getaDao().create(this.yorum);
        clearForm();
        return "yorum";
    }

    public String updateForm(Yorum yorum) {
        this.yorum = yorum;
        return "yorum";
    }

    public void clearForm() {
        this.yorum = new Yorum();

    }

    public String update() {
        this.yorumDao.update(this.yorum);
        this.clearForm();
        return "yorum";
    }

    public String delete(Yorum yorum) {
        this.yorum = yorum;
        this.getaDao().delete(yorum.getId());
        clearForm();
        return "confirm_delete";

    }

    public String delete() {
        this.getaDao().delete(yorum.getId());
        clearForm();
        return "yorum";

    }

}
