/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Admin;
import cn.edu.sdut.softlab.entity.User;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

/**
 *
 * @author huanlu
 */
@ManagedBean(name = "admController")
@RequestScoped
public class AdminController {
    
    @Inject
    Logger logger;
    
    @Inject
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Inject
    FacesContext facesContext;
    
    private Admin currentAdmin = new Admin();

    public Admin getCurrentAdmin() {
        return currentAdmin;
    }

    public void setCurrentAdmin(Admin currentAdmin) {
        this.currentAdmin = currentAdmin;
    }
    
    public String modifyMySelf(Admin loginAdmin) throws Exception {
        logger.log(Level.INFO, "Admin information modify:{0}", loginAdmin.toString());
        try {
            utx.begin();
            currentAdmin.setId(loginAdmin.getId());
            em.merge(currentAdmin);
            return "";
        } finally {
            utx.commit();
            facesContext.addMessage("", new FacesMessage("您输入的信息已经修改!"));
        }
    }
}
