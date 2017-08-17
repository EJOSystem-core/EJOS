package cn.edu.sdut.softlab.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import cn.edu.sdut.softlab.entity.Question;
import cn.edu.sdut.softlab.entity.Team;

/**
 * @author huanlu
 */
@Named("questionService")
public class QuestionFacade extends AbstractFacade<Question> {

    public QuestionFacade() {
        super(Question.class);
    }

    public List<Question> findQuestionsByTeam(Integer teamId) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("teamId", teamId);
        return findByNamedQuery("Question.findByTeamId", parameters);
    }

    public Question findQuestionsById(Integer ques_id) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("id", ques_id);
        return findSingleByNamedQuery("Question.findById", parameters, Question.class).get();
    }

    public Question findQuestionsByName(String name) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("question", name);
        return findSingleByNamedQuery("Question.findByQuestion", parameters, Question.class).get();
    }

    public Question findSpecifiedQuestionByQuestion(String question) {
      System.out.println("log print ----------------findSpecifiedQuestionByQuestion is called");
      Map<String, Object> parameters = new HashMap<>();
      parameters.put("question", question);
      return findSingleByNamedQuery("Question.findByQuestion", parameters, Question.class).get();
    }

    /**
     * 2017-08-07
     * @return
     */
    public List<Question> findAllQuestionsWithoutTeam() {
      System.out.println("Question Facade:log print ----------------findAllQuestionWithoutTeam is called\n");
      return findAll();
    }

    /**
     * 2017-08-07
     * @param paramTeam
     * @return
     */
    public List<Question> findAllQuestionsWithTeam(Team paramTeam) {
      Map<String, Object> parameters = new HashMap<>();
      parameters.put("team", paramTeam);
      return findByNamedQuery("Question.findByTeam", parameters);
    }

}
