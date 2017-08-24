/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.interceptor;

import cn.edu.sdut.softlab.controller.LoginController;
import cn.edu.sdut.softlab.qualifiers.TeacherAudit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author huanlu
 */
@TeacherAudit
@Interceptor
public class TeacherAuditInterceptor {

    @Inject
    LoginController loginController;

    @Inject
    Logger logger;
    
    @Inject
    FacesContext facesContext;

    @AroundInvoke
    public Object doAudit(InvocationContext ctx) throws Exception {
        logger.info("Begin Teacher Audit Interceptor!");
        logger.log(Level.INFO, "getTarget:{0}", ctx.getTarget().toString());
        if (loginController.getCurrentUser().getLevel().equals("Teahcer")
                || loginController.getCurrentUser().getLevel().equals("Admin")) {
            return ctx.proceed();
        }
        facesContext.addMessage(null, new FacesMessage("您没有权限操作!"));
        return "/home.xhtml?faces-redirect=true";
    }
}
