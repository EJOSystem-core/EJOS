package cn.edu.sdut.softlab.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import cn.edu.sdut.softlab.entity.Achievement;
import cn.edu.sdut.softlab.entity.Question;
import cn.edu.sdut.softlab.entity.Student;
import javax.ejb.Stateless;

/**
 * @author GaoYisheng 2017年8月9日 TODO
 */
@Named("achievementService")
@Stateless
public class AchievementFacade extends AbstractFacade<Achievement> {

    public AchievementFacade() {
        super(Achievement.class);
    }

    public Achievement findByQuestionAndStudent(Question question, Student stu) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("question", question);
        parameters.put("student", stu);
        return findSingleByNamedQuery("Achievement.findByQuestionAndStudent", parameters, Achievement.class).get();
    }

}
