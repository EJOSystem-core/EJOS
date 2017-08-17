/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.interceptor;

import cn.edu.sdut.softlab.qualifiers.AdminAudit;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

/**
 *
 * @author huanlu
 */
@AdminAudit(isAdmin = true)
@Interceptor
public class AdminAuditInterceptor implements Serializable{
    
    @Inject
    Logger logger;
    
    @AroundInvoke
    public Object doAudit(InvocationContext ctx) throws Exception{
        logger.info("Begin Admin Audit Interceptor!");
        logger.log(Level.INFO, "getTarget:{0}", ctx.getTarget().toString());
        return ctx.proceed();
    }
}   
