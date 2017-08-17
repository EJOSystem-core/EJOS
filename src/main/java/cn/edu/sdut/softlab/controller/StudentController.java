/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.entity.Team;
import cn.edu.sdut.softlab.entity.User;
import cn.edu.sdut.softlab.service.StudentFacade;
import cn.edu.sdut.softlab.util.CSVUtil;
import cn.edu.sdut.softlab.validator.StringIllegalValidator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author huanlu
 */
@RequestScoped
@Named("stuController")
public class StudentController {

    @Inject
    Logger logger;

    @Inject
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Inject
    StudentFacade studentSerivce;
    
    @Inject
    FacesContext facesContext;
    
    @Inject
    CSVUtil csv;
    
    @Inject
    StringIllegalValidator stringValidator;

    //因为学生表班级字段不能不为空,设置默认为1
    private Student currentstu = new Student(new Team(1));

    public Student getCurrentstu() {
        return currentstu;
    }

    public void setCurrentstu(Student currentstu) {
        this.currentstu = currentstu;
    }
    
    //绑定前台要导出的班级id
    private Integer tempTeamId;

    public Integer getTempTeamId() {
        return tempTeamId;
    }

    public void setTempTeamId(Integer tempTeamId) {
        this.tempTeamId = tempTeamId;
    }
    
    //绑定前台指定的文件生成目录
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public List<Student> getAll() throws Exception {
        try {
            utx.begin();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Student.class));
            return em.createQuery(cq).getResultList();
        } finally {
            utx.commit();
        }
    }
    
    /**
     * 根据前台选定的班级后台对应打印出对应的学生信息
     */
    public void getStudentsByTeam() throws Exception {
        /*自定实现,未再次添加NamedQuery,*/
//        CriteriaQuery criteria = em.getCriteriaBuilder().createQuery();
//        Root<Student> stu = criteria.from(Student.class);
//        criteria.select(stu).where(
//                em.getCriteriaBuilder().equal(stu.get("team"), this.getTemTeamId())
//        );
//        Query query = em.createQuery(criteria);

            /*调用Service层,实体类添加NamedQuery*/
        List<Student> stus = studentSerivce.findByTeam(tempTeamId);
        stus.forEach((s) -> {
            System.out.println("cn.edu.sdut.softlab.controller.StudentController.getStudentsByTeam()" + s.toString());
        });
        try {
            csv.writeWithStudentByTeam(path, ',', this.getTempTeamId());
        } catch (Exception ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            facesContext.addMessage("", new FacesMessage("您选中的班级的学生信息已经导出"));
        }
        //csv.getColumnName("student");
        //csv.test("student");
    }
    
    /**
     * 通过学生列表来修改学生信息
     * @param event
     * @throws Exception 
     */
    public void onRowEdit(RowEditEvent event) throws Exception {
        Student editStudent = (Student) event.getObject();
        currentstu.setId(editStudent.getId());
        logger.log(Level.INFO, "Student Edit!~~~~~~~~~~~~~~~~~~~~{0}", editStudent.toString());
        logger.log(Level.INFO, "current information:    {0}", currentstu.toString());
        try {
            utx.begin();
            em.merge(currentstu);
        } finally {
            utx.commit();
            FacesMessage fms = new FacesMessage("Student modify", event.getObject().getClass().getName());
            FacesContext.getCurrentInstance().addMessage(null, fms);
            currentstu = null;
            event = null;
        }
    }
    
    public void addSingleStudent()throws Exception{
        try {
            utx.begin();
            em.persist(currentstu);
            logger.log(Level.INFO, "Single Student Add Called:{0}", currentstu.toString());
            facesContext.addMessage(null, new FacesMessage("添加成功"));
        } finally{
            utx.commit();
        }
    }
    
    /**
     * 通过csv文件添加学生时调用
     * @param s
     * @throws Exception 
     */
    public void addSingleStudentByList(Student s) throws Exception{
        try {
            Integer status1 = utx.getStatus();
            logger.info("Status1" + status1.toString());
            utx.begin();
            Integer status2 = utx.getStatus();
            logger.info("Status2" + status2.toString());
            studentSerivce.create(s);
            utx.commit();
        } finally{
            logger.log(Level.INFO, "{0} \u6dfb\u52a0\u6210\u529f!", s.getName());
        }
    }

    /**
     * 列表修改暂时取消调用
     * @param event 
     */
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((User) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        currentstu = null;
    }
    
    public void modify() throws Exception {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Student loginStudent = (Student) session.getAttribute("currentUser");
        loginStudent.toString();
        currentstu.setStudentNum(loginStudent.getStudentNum());
        try {
            utx.begin();
            em.merge(currentstu);
            logger.log(Level.INFO, "Student Edit:{0}", currentstu.toString());
        } finally {
            currentstu = null;
            utx.commit();
        }
    }
    
    public void stuAddValidator(FacesContext fc, UIComponent component, Object value) {
        stringValidator.AddValidator(value);
        List<Student> stus = studentSerivce.findAll();
        stus.stream().filter((i) -> (value.equals(i.getName()))).forEachOrdered((_item) -> {
            throw new ValidatorException(new FacesMessage("您要添加的物品已有，请验证确定后再次添加！"));
        });
    }

    public String modifyMySelf(Student loginStudent) throws Exception {
        logger.log(Level.INFO, "Student information modify:{0}", loginStudent.toString());
        try {
            utx.begin();
            currentstu.setId(loginStudent.getId());
            currentstu.setStudentNum(loginStudent.getStudentNum());
            em.merge(currentstu);
            return "";
        } finally {
            utx.commit();
            facesContext.addMessage("", new FacesMessage("您输入的信息已经修改!"));
        }
    }

    public void delete(Student stu) throws Exception {
        logger.log(Level.INFO, "{0}", stu.toString());
        Student delectStu = studentSerivce.findByStuId(stu.getId());
        try {
            utx.begin();
            logger.log(Level.INFO, "Student Delete Called:{0}", delectStu.toString());
            studentSerivce.remove(delectStu);
            em.flush();
        } catch (NotSupportedException | SystemException e) {
            throw new RuntimeException(e);
        } finally {
            utx.commit();
        }
    }

}
