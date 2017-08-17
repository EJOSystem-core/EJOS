/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.interceptor;

import cn.edu.sdut.softlab.controller.LoginController;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;
import cn.edu.sdut.softlab.qualifiers.Secure;
import javax.interceptor.Interceptor;
import cn.edu.sdut.softlab.qualifiers.TeacherAudit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author huanlu
 */
@TeacherAudit
@ApplicationScoped
public class TeacherAuditInterceptor {

    @Inject
    LoginController loginController;

    @Inject
    Logger logger;

    @AroundInvoke
    public Object doAudit(InvocationContext ctx) throws Exception {
        logger.info("Begin Teacher Audit Interceptor!");
        logger.log(Level.INFO, "getTarget:{0}", ctx.getTarget().toString());
        if (loginController.getCurrentUser().getLevel().equals("Teahcer")
                || loginController.getCurrentUser().getLevel().equals("Admin")) {
            return ctx.proceed();
        }
        return "/home.xhtml?faces-redirect=true";
    }
}
