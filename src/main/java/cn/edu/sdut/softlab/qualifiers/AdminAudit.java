/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.qualifiers;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.interceptor.InterceptorBinding;
import org.apache.deltaspike.security.api.authorization.SecurityBindingType;

/**
 *
 * @author huanlu
 */
@Target({TYPE,METHOD})
@Retention(RUNTIME)
@InterceptorBinding
public @interface AdminAudit {
    
    boolean isAdmin() default false;
    
}
