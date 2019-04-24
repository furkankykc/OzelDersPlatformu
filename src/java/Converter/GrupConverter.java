/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.GrupDAO;
import Entity.Grup;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "grupConverter")
public class GrupConverter implements Converter{

    private GrupDAO grupDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getGrupDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Grup grup = (Grup) value;
        return Integer.toString(grup.getId());
    }

    public GrupDAO getGrupDao() {
        if(grupDao==null)
            grupDao =new GrupDAO();
        return grupDao;
    }
    
    
}
