/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.service;

import cn.edu.sdut.softlab.entity.News;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author huanlu
 */
@Named
@ApplicationScoped
public class NewsFacade extends AbstractFacade<News>{
    
    public NewsFacade() {
        super(News.class);
    }
    
    public News findById(Integer id){
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("id", id);
        return findSingleByNamedQuery("News.findById", parameters, News.class).get();
    }
    
    public News findByStatus(String status){
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("status", status);
        return findSingleByNamedQuery("News.findByStatus", parameters, News.class).get();
    }
    
    public News findByTeamId(Integer team_id){
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("teamId", team_id);
        return findSingleByNamedQuery("News.findByTeamId", parameters, News.class).get();
    }
    
    public List<News> getNewsByStuId(Integer stu_id){
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("stu_id", stu_id);
        return findByNamedQuery("News.findByStuId", parameters);
    }
    
}
