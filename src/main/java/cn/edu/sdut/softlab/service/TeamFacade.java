/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.service;

import cn.edu.sdut.softlab.entity.Team;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author huanlu
 */
public class TeamFacade extends AbstractFacade<Team> {

    public TeamFacade() {
        super(Team.class);
    }

    public Team findByName(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        return findSingleByNamedQuery("Team.findByName", parameters, Team.class).get();
    }
    
    public Team findById(Integer id) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("id", id);
        return findSingleByNamedQuery("Team.findById", parameters, Team.class).get();
    }
}
