/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Achievement;
import cn.edu.sdut.softlab.service.AchievementFacade;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;

/**
 *
 * @author huanlu
 */
@ManagedBean(name = "achieveController")
@RequestScoped
public class AchievementController {

    @Inject
    Logger logger;

    @Inject
    AchievementFacade achievementSevice;

    @Inject
    UserTransaction utx;

    @Inject
    EntityManager em;
    
    private List<Achievement> filterAchievements;

    public List<Achievement> getFilterAchievements() {
        return filterAchievements;
    }

    public void setFilterAchievements(List<Achievement> filterAchievements) {
        this.filterAchievements = filterAchievements;
    }
    
    public List<Achievement> findAll() throws Exception {
        try {
            utx.begin();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Achievement.class));
            return em.createQuery(cq).getResultList();
        } finally {
            utx.commit();
        }
    }
}
