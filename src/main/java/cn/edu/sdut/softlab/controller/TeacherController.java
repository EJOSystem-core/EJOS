/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Teacher;
import cn.edu.sdut.softlab.entity.User;
import cn.edu.sdut.softlab.qualifiers.AdminAudit;
import cn.edu.sdut.softlab.qualifiers.Secure;
import cn.edu.sdut.softlab.qualifiers.TeacherAudit;
import cn.edu.sdut.softlab.service.TeacherFacade;
import cn.edu.sdut.softlab.validator.StringIllegalValidator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author huanlu
 */
@ManagedBean(name = "teaController")
@RequestScoped
public class TeacherController {

    @Inject
    Logger logger;

    @Inject
    TeacherFacade teacherService;

    @Inject
    UserTransaction utx;

    @Inject
    EntityManager em;

    @Inject
    FacesContext facesContext;

    @Inject
    StringIllegalValidator stringValidator;

    private List<Teacher> filterTeachers;

    public List<Teacher> getFilterTeachers() {
        return filterTeachers;
    }

    public void setFilterTeachers(List<Teacher> filterTeachers) {
        this.filterTeachers = filterTeachers;
    }

    private Teacher currentTea = new Teacher();

    public Teacher getCurrentTea() {
        return currentTea;
    }

    public void setCurrentTea(Teacher currentTea) {
        this.currentTea = currentTea;
    }

    private Teacher deleteTeacher = new Teacher();

    private String deletename;

    public String getDeletename() {
        return deletename;
    }

    public void setDeletename(String deletename) {
        this.deletename = deletename;
    }

    public List<Teacher> getAll() throws Exception {
        try {
            utx.begin();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Teacher.class));
            return em.createQuery(cq).getResultList();
        } finally{
            utx.commit();
        }
    }
    
    @AdminAudit
    public void onRowEdit(RowEditEvent event) throws Exception {
        Teacher editTeacher = (Teacher) event.getObject();
        currentTea.setId(editTeacher.getId());
        logger.log(Level.INFO, "Teacher Edit!~~~~~~~~~~~~~~~~~~~~{0}", editTeacher.toString());
        logger.log(Level.INFO, "{0}current information:    ", currentTea.toString());
        try {
            utx.begin();
            em.merge(currentTea);
        } finally {
            utx.commit();
            FacesMessage fms = new FacesMessage("Teacher modify", event.getObject().getClass().getName());
            FacesContext.getCurrentInstance().addMessage(null, fms);
            currentTea = null;
            event = null;
        }
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((User) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        currentTea = null;
    }
    
    @AdminAudit
    public void add() throws Exception {
        if (!currentTea.getName().equals("")) {
            try {
                utx.begin();
                teacherService.create(currentTea);
                logger.log(Level.INFO, "Added {0}", currentTea);
            } finally {
                utx.commit();
            }
        } else {
            facesContext.addMessage(null, new FacesMessage("添加失败!"));
        }
    }
    
    @AdminAudit
    public void delete() throws Exception {
        try {
            Teacher deleteTea = teacherService.findByTeaName(deletename);
            if (deleteTea != null) {
                deleteTeacher = deleteTea;
            }
            utx.begin();
            teacherService.remove(deleteTeacher);
            System.out.println("usertransation:" + utx.getStatus());
            utx.commit();
            facesContext.addMessage(null, new FacesMessage("您选中的教师已从数据库中删除"));
        } finally {
            logger.log(Level.INFO, "Teacher Delete Called:{0}", deleteTeacher.toString());
        }
    }
    
    public void teaAddValidator(FacesContext fc, UIComponent component, Object value) {
        stringValidator.AddValidator(value);
        List<Teacher> teachers = teacherService.findAll();
        teachers.stream().filter((i) -> (value.equals(i.getName()))).forEachOrdered((_item) -> {
            throw new ValidatorException(new FacesMessage("您要添加的物品已有，请验证确定后再次添加！"));
        });
    }
    
    @TeacherAudit
    public String modifyMySelf(Teacher loginTeacher) throws Exception {
        logger.log(Level.INFO, "Student information modify:{0}", loginTeacher.toString());
        try {
            utx.begin();
            currentTea.setId(loginTeacher.getId());
            currentTea.setTeacherNum(loginTeacher.getTeacherNum());
            em.merge(currentTea);
            return "";
        } finally {
            utx.commit();
            facesContext.addMessage("", new FacesMessage("您输入的信息已经修改完成!"));
        }
    }
}
