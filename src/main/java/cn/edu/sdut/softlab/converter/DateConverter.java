/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.converter;

import cn.edu.sdut.softlab.entity.News;
import cn.edu.sdut.softlab.util.DateUtil;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author huanlu
 */
@ManagedBean(name = "dateConverter")
@FacesConverter(forClass = News.class, value = "dateConverter")
public class DateConverter implements Converter, Serializable {

    @Inject
    DateUtil dateUtil;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            Date date = dateUtil.strToDateLong(value);
            System.out.println("1111" + date.toString());
            return date;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Date) {
            return value.toString();
        }
        return null;
    }

}
