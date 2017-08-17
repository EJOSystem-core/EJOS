/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.service.StudentFacade;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 *
 * @author huanlu
 */
@ApplicationScoped
@ManagedBean(name = "settings")
public class SettingsController {
    
    @Inject
    StudentFacade studentService;
    
    public List<String> getAllMainTheme(){
        String [] maintheme = {"afterdark", "afternoon", "afterwork", "aristo","black-tie", "blitzer", 
                            "bluesky", "bootstrap", "casablanca", "cupertino", "cruze", "dark-hive", 
                            "delta", "dot-luv", "eggplant", "excite-bike", "flick", "glass-x", "home", 
                            "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc", "omega", 
                            "overcast", "pepper-grinder", "redmond", "rocket", "sam", "smoothness", 
                            "south-street", "start", "sunny", "swanky-purse", "trontastic", "ui-darkness", "ui-lightness", "vader"};
         List<String> main_theme = java.util.Arrays.asList(maintheme);
         return main_theme;
    }
    
}
