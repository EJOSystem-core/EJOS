package cn.edu.sdut.softlab.controller;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;

import cn.edu.sdut.softlab.entity.User;
import cn.edu.sdut.softlab.util.UserProducers;
import java.util.logging.Level;

@SessionScoped
@Named("login")
public class LoginController implements Serializable {

    private static final long serialVersionUID = 7965455427888195913L;

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
    private Credentials credentials;

    @Inject
    FacesContext facesContext;

    @Inject
    UserProducers userProducers;

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     *
     * 处理登录逻辑.
     */
    public String login() {
        userProducers.setLevel(this.getLevel());
        currentUser = userProducers.getUser();
        currentUser.setLevel(this.level);
        logger.log(Level.INFO, "Login:{0}", currentUser.toString());
        facesContext.addMessage(null, new FacesMessage("Welcome, " + currentUser.getName()));
        return "/home.xhtml?faces-redirect=true";
    }

    /**
     * 系统登录初始化提示
     */
    @PostConstruct
    public void init() {
        System.out.println("LoginController begin construct......");
    }

    /**
     * 处理退出登录逻辑.
     *
     * @return
     */
    public String logout() {
        logger.log(Level.INFO, "LogOut:{0}", currentUser.toString());
        facesContext.addMessage(null, new FacesMessage("Goodbye, " + currentUser.getName()));
        currentUser = null;
        return "/login.xhtml?faces-redirect=true";
    }

    /**
     * 判断用户是否登录.
     *
     * @return true：已经登录；false：没有登录
     */
    public boolean isLoggedIn() {
        return currentUser != null;//才看明白，null != null 没登录！
    }

    public void redirectPath() {
        FacesContext context = FacesContext.getCurrentInstance();
        ConfigurableNavigationHandler handler = (ConfigurableNavigationHandler) context.getApplication().getNavigationHandler();
        handler.performNavigation("home");
    }
    
    /**
     * 判断时候是登录状态浏览当前页面决定是否展示当前视图页面
     * @param event 
     */
    public void checkLogin(ComponentSystemEvent event) {
        if (!this.isLoggedIn()) {
            redirectPath();
        }
    }
    
    /**
     * 验证登录者是否为管理员决定是否展示视图页面
     * @param event 
     */
    public void checkAdminUser(ComponentSystemEvent event) {
        if (this.isLoggedIn()) {
            if (!this.getCurrentUser().getLevel().equals("Admin")) {
                redirectPath();
            }
        }
    }
    
    /**
     * 验证登录者是否为老师决定是否展示视图页面
     * @param event 
     */
    public void checkTeacherUser(ComponentSystemEvent event) {
        if (this.isLoggedIn()) {
            if (!this.getCurrentUser().getLevel().equals("Teacher")) {
                redirectPath();
            }
        }
    }

    public boolean isAdminUser() {
        return currentUser.getLevel().equals("Admin");
    }

    public boolean isTeacherUser() {
        return currentUser.getLevel().equals("Teacher");
    }

    public boolean isAdminOrTeacherUser() {
        if (currentUser.getLevel().equals("Admin") || currentUser.getLevel().equals("Teacher")) {
            return true;
        }
        return false;
    }

    /**
     * 异步根据前台绑定的根据登录学生的id查询,返回对应的
     *
     * @param event
     */
//    public String profileReturn(ActionEvent event) {
//        Integer id = (Integer) event.getComponent().getAttributes().get("loginid");
//        Student loginStudent = studentService.findByStuId(id);
//        logger.info("Student:" + loginStudent.toString());
//        return "/student/student_modify.jsf?id=" + id + "&name = " + loginStudent.getName();
//    }
//    public void studentModifyLogin(Integer id, String name) {
//        Student checkStu = studentService.findByStuId(id);
//        if (name.equals(checkStu.getName())) {
//            
//        }
//    }
}
