/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.util;

import cn.edu.sdut.softlab.controller.Credentials;
import cn.edu.sdut.softlab.entity.Admin;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;
import cn.edu.sdut.softlab.qualifiers.Preferred;
import cn.edu.sdut.softlab.service.AdminFacade;
import cn.edu.sdut.softlab.service.StudentFacade;
import cn.edu.sdut.softlab.service.TeacherFacade;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import cn.edu.sdut.softlab.entity.User;
import java.io.Serializable;
import javax.inject.Named;

/**
 * 
 * @author huanlu
 */
@Named
@RequestScoped
public class UserProducers implements Serializable {

    public UserProducers() {
        System.out.print("UserProducers constructor called");
    }
    
    private String level;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
    
    @Inject
    Logger logger;

    @Inject
    @Default
    Credentials credentials;

    @Inject
    AdminFacade adminService;

    @Inject
    TeacherFacade teacherService;

    @Inject
    StudentFacade studentService;

    @Produces
    @Preferred
    @SessionScoped
    public User getUser() {
        switch (this.level) {
            case "Admin":
                return adminService.findByIdAndPassword(credentials.getNO().intValue(), credentials.getPassword());
            case "Teacher":
                return teacherService.findByTeacherNoAndPassword(credentials.getNO(), credentials.getPassword());
            case "Student":
                return studentService.findByStuNOAndPassword(credentials.getNO(), credentials.getPassword());
            default:
                return null;
        }
    }

}
