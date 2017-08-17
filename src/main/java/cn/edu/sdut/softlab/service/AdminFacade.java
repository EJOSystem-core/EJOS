/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.service;

import cn.edu.sdut.softlab.entity.Admin;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Named;

/**
 *
 * @author huanlu
 */
@Named("adminfacade")
public class AdminFacade extends AbstractFacade<Admin>{
    
    public AdminFacade() {
        super(Admin.class);
    }
    
    public Admin findByAdminId(Integer id) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("id", id);
        return findSingleByNamedQuery("Admin.findById", parameters, Admin.class).get();
    }
    
    public Admin findByIdAndPassword(Integer id, String password) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("id", id);
        parameters.put("password", password);
        return findSingleByNamedQuery("Admin.findByIdAndPassword", parameters, Admin.class).get();
    }

    /**
     *
     * @param name
     * @param password
     * @return
     */
    public Admin findByNameAndPassword(String name, String password) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("name", name);
        parameters.put("password", password);
        return findSingleByNamedQuery("Admin.findByNameAndPassword", parameters, Admin.class).get();
    }
    
}
