/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.KategoriDAO;
import Entity.Kategori;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "kategoriConverter")
public class KategoriConverter implements Converter{

    private KategoriDAO kategoriDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getKategoriDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Kategori kategori = (Kategori) value;
        return Integer.toString(kategori.getId());
    }

    public KategoriDAO getKategoriDao() {
        if(kategoriDao==null)
            kategoriDao =new KategoriDAO();
        return kategoriDao;
    }
    
    
}
