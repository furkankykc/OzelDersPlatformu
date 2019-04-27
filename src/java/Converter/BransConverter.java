/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.BransDAO;
import Entity.Brans;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "bransConverter")
public class BransConverter implements Converter{

    private BransDAO bransDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getBransDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Brans brans = (Brans) value;
        return Integer.toString(brans.getId());
    }

    public BransDAO getBransDao() {
        if(bransDao==null)
            bransDao =new BransDAO();
        return bransDao;
    }
    
    
}
