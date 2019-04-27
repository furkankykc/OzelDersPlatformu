/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import DAO.FileDAO;
import Entity.File;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author furkankykc
 */
@FacesConverter(value = "fileConverter")
public class FileConverter implements Converter{

    private FileDAO fileDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return this.getFileDao().get(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        File file = (File) value;
        return Integer.toString(file.getId());
    }

    public FileDAO getFileDao() {
        if(fileDao==null)
            fileDao =new FileDAO();
        return fileDao;
    }
    
    
}
