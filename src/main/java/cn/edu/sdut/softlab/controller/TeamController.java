/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Team;
import cn.edu.sdut.softlab.qualifiers.AdminAudit;
import cn.edu.sdut.softlab.service.TeamFacade;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author huanlu
 */
@RequestScoped
@Named("teamController")
public class TeamController {
    
    @Inject
    EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Inject
    TeamFacade teamService;
    
    private Team currentTeam = new Team();

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }
    
    public List<Team> getAll() throws Exception{
        try {
            utx.begin();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Team.class));
            return em.createQuery(cq).getResultList();
        }
        finally {
            utx.commit();
        }
    }
    
    @AdminAudit
    public void edit(){
        
    }
    
    @AdminAudit
    public void add(){
    
    }
}
