/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.qualifiers;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import javax.transaction.Transactional;

/**
 *
 * @author huanlu
 */
@InterceptorBinding
@Target({TYPE,METHOD})
@Retention(RUNTIME)
@Transactional
public @interface Secure {
    
    @Nonbinding
    String[] rolesAllowed() default {};
}
