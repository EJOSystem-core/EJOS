/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.entity;

import javax.persistence.Transient;

/**
 * 
 * @author huanlu
 */
public interface User {
    
    @Transient
    String level = "";
    
    void setLevel(String level);

    String getLevel();
            
    String getPassword();
    
    String getName();
}
