/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.DersDAO;
import Entity.Ders;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "dersConverter")
public class DersConverter implements Converter{

    private DersDAO dersDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getDersDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Ders ders = (Ders) value;
        return Integer.toString(ders.getId());
    }

    public DersDAO getDersDao() {
        if(dersDao==null)
            dersDao =new DersDAO();
        return dersDao;
    }
    
    
}
