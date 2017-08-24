/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.News;
import cn.edu.sdut.softlab.entity.Question;
import cn.edu.sdut.softlab.entity.Record;
import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.service.NewsFacade;
import cn.edu.sdut.softlab.service.QuestionFacade;
import cn.edu.sdut.softlab.service.RecordFacade;
import cn.edu.sdut.softlab.service.StudentFacade;
import cn.edu.sdut.softlab.service.TeamFacade;
import cn.edu.sdut.softlab.util.DateUtil;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.UserTransaction;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author huanlu
 */
@Named(value = "newsController")
@ApplicationScoped
public class NewsController {
    
    @Inject
    Logger logger;
    
    @Inject
    UserTransaction utx;
    
    @Inject
    EntityManager em;
    
    @Inject
    FacesContext facesContext;
    
    @Inject
    DateUtil dateUtil;
    
    @Inject
    TeamFacade teamService;
    
    @Inject
    QuestionFacade questionService;
    
    @Inject
    RecordFacade recordService;
    
    @Inject
    NewsFacade newsService;
    
    @Inject
    StudentFacade studentService;
    
    @Inject
    LoginController loginController;
    
    private News currentnews = new News();

    public News getCurrentnews() {
        return currentnews;
    }

    public void setCurrentnews(News currentnews) {
        this.currentnews = currentnews;
    }
    
    public List<News> getAll() throws Exception{
        try {
            utx.begin();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(News.class));
            return em.createQuery(cq).getResultList();
        } finally{
            utx.commit();
        }
    }
    
    /**
     * 根绝登录的用户身份,返回对应的消息数量
     * @return 
     */
    public Integer getNewsNum(){
        if (loginController.getCurrentUser().getLevel().equals("Admin") 
        		|| loginController.getCurrentUser().getLevel().equals("Teacher")) {
            return 0;
        }
        Student loginStu = studentService.findStudentByName(loginController.getCurrentUser().getName());
        List<News> newss = newsService.getNewsByStuId(loginStu.getId());
        return newss.size();
    }
    
    /**
     * 向前台返回消息列表
     * @return 
     */
    public List<News> getNewsByStu(){
        if (loginController.getCurrentUser().getLevel().equals("Admin")
        		|| loginController.getCurrentUser().getLevel().equals("Teacher")) {
            return null;
        }
        Student loginStu = studentService.findStudentByName(loginController.getCurrentUser().getName());
        return newsService.getNewsByStuId(loginStu.getId());
    }
    
    /**
     * 根据Record记录中未通过的记录向对应学生发送消息
     * @throws Exception 
     */
    public void addNewsByRecord() throws Exception {
        try {
            utx.begin();
            List<Record> records = recordService.findRecordsByStuIdAndStatus("未完成", 1);
            for (Record record : records) {
                News currentNews = new News();
                currentNews.setStudent(studentService.findByStuId(1));
                currentNews.setContent("您还有未完成的题目,请按时完成!");
                currentNews.setStarttime(new Date());
                currentNews.setEndtime(questionService.findQuestionsById(record.getQuestionId()).getDeadline());
                currentNews.setTitle(questionService.findQuestionsById(record.getQuestionId()).getQuestion() + "未完成");
                currentNews.setStatus("未完成");
                newsService.create(currentNews);
            }
        }
        finally{
            utx.commit();
        }
    }
    
    /**
     * 添加题目是自动调用
     * @param currentQuestion
     * @throws Exception 
     */
    public void addNewsBynewQuestion(Question currentQuestion) throws Exception{
        try {
            utx.begin();
            News currentNews = new News();
            currentNews.setStarttime(dateUtil.getNowDate());
            currentNews.setContent("您有新的作业要完成!");
            currentNews.setEndtime(currentQuestion.getDeadline());
            currentNews.setTitle("有新的作业");
            currentNews.setStatus("未完成");
            List<Student> stus = studentService.findByTeam(currentQuestion.getTeam().getId());
            for (Student stu : stus) {
                currentNews.setStudent(stu);
                logger.log(Level.INFO, "News Add Called: {0}", currentNews.toString());
                em.persist(currentNews);
                currentNews = null; 
            }
        }finally{
            utx.commit();
        }
    }
    
    public void onRowEdit(RowEditEvent event) throws Exception {
        News editNews = (News) event.getObject();
        currentnews.setId(editNews.getId());
        logger.log(Level.INFO, "News Edit!~~~~~~~~~~~~~~~~~~~~{0}", editNews.toString());
        logger.log(Level.INFO, "current information:    {0}", currentnews.toString());
        try {
            utx.begin();
            em.merge(currentnews);
        } finally {
            utx.commit();
            FacesMessage fms = new FacesMessage("News modify", event.getObject().getClass().getName());
            FacesContext.getCurrentInstance().addMessage(null, fms);
            currentnews = null;
            event = null;
        }
    }
    
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((News)event.getObject()).getTitle());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        currentnews = null;
    }
}
