package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.Question;
import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.entity.Team;
import cn.edu.sdut.softlab.service.QuestionFacade;
import cn.edu.sdut.softlab.service.TeamFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;

/**
 * @author huanlu
 */
@RequestScoped
@Named("questionController")
public class QuestionController {

	@Inject
	private Logger logger;

	@Inject
	private UserTransaction utx;

	@Inject
	EntityManager em;

	@Inject
	FacesContext facesContext;

	@Inject
	NewsController newsController;
	
	@Inject
	LoginController loginController;
	
	@Inject
	TeamFacade teamService;

	@Inject
	QuestionFacade questionService;

	private Student currentStu ;//= (Student) loginController.getCurrentUser();

	@PostConstruct
	public void init(){
		Student s = (Student) loginController.getCurrentUser();
		this.currentStu = s ;
	}
	
	public void setCurrentStu(Student s) {
		this.currentStu = s;
	}

	public Student getCurrentStu() {
		return currentStu;
	}

	private String delectName;

	public String getDelectName() {
		return delectName;
	}

	public void setDelectName(String delectName) {
		this.delectName = delectName;
	}

	private Question currentquestion = new Question(new Team(1));

	public Question getCurrentquestion() {
		return currentquestion;
	}

	public void setCurrentquestion(Question currentquestion) {
		this.currentquestion = currentquestion;
	}

	private List<Question> filteredQuestions;

	public List<Question> getFilteredQuestions() {
		return filteredQuestions;
	}

	public void setFilteredQuestions(List<Question> filteredQuestions) {
		this.filteredQuestions = filteredQuestions;
	}

	public List<Question> findAll() throws Exception {
		try {
			utx.begin();
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Question.class));
			return em.createQuery(cq).getResultList();
		} finally {
			utx.commit();
		}
	}

	public List<Question> findItemBanksByTeam() {
		return null;
	}

	public void addQuestion() throws Exception {
		try {
			utx.begin();
			logger.info(currentquestion.toString());
			questionService.create(currentquestion);
			facesContext.addMessage(null, new FacesMessage("题目添加成功!"));
			logger.log(Level.INFO, "Quesstion Add Called:{0}", currentquestion.toString());
		} finally {
			utx.commit();
			// newsController.addNewsBynewQuestion(currentquestion);
			// currentquestion = null;
		}
	}

	public void deleteQuestion() throws Exception {
		try {
			utx.begin();
			Question delectQues = questionService.findQuestionsByName(delectName);
			logger.info(delectQues.toString());
			questionService.remove(delectQues);
			facesContext.addMessage(null, new FacesMessage("题目删除成功!"));
			logger.log(Level.INFO, "Quesstion Add Called:{0}", currentquestion.toString());
		} finally {
			utx.commit();
		}
	}

	public List<Question> getAllQuestionsWithTeam() throws Exception {
		try {
			utx.begin();
			Team paramTeam = currentStu.getTeam();
			return questionService.findAllQuestionsWithTeam(paramTeam);
		} finally {
			utx.commit();
		}
	}

	public List<Question> getAllQuestionsWithoutTeam() throws Exception {
		try {
			utx.begin();
			logger.info("getAllQuestionsWithoutTeam---------------in Manager is calledddd");

			return questionService.findAllQuestionsWithoutTeam();

		} finally {
			utx.commit();
		}
	}

	/**
	 * 处理当前问题值改变逻辑.
	 */
	public void selectedChanged(ValueChangeEvent event) {
		System.out.println("logPrint >> ---------------QuestionManager-selectedChanged-value-is:"
				+ event.getNewValue().toString());

		String introduce = questionService.findSpecifiedQuestionByQuestion(event.getNewValue().toString())
				.getIntroduce();
		facesContext.addMessage(null, new FacesMessage("当前问题是： " + event.getNewValue().toString()));
		facesContext.addMessage(null, new FacesMessage("题目要求： " + introduce));
	}
}
