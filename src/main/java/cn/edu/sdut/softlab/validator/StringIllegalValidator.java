/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.validator;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author huanlu
 */
@ManagedBean
public class StringIllegalValidator {

    public void AddValidator(Object value) {
        if (((String) value).contains(":") | ((String) value).contains("~")
                | ((String) value).contains("！") | ((String) value).contains("@")
                | ((String) value).contains("#") | ((String) value).contains("$")
                | ((String) value).contains("%") | ((String) value).contains("^")
                | ((String) value).contains("&") | ((String) value).contains("*")
                | ((String) value).contains("(") | ((String) value).contains(")")
                | ((String) value).contains("-") | ((String) value).contains("{")
                | ((String) value).contains("}") | ((String) value).contains("【")
                | ((String) value).contains("]")) {
            throw new ValidatorException(new FacesMessage("您输入的名称含有不合法字符！"));
        }
    }
}
