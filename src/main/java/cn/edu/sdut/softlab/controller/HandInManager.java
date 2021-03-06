/*
 * file_name: HandInManager.java
 *
 * Copyright GaoYisheng Corporation 2017
 *
 * License：
 * date：2017年6月6日
 *       https://www.gaoyisheng.site
 *       https://github.com/timo1160139211
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.sdut.softlab.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.transaction.UserTransaction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import cn.edu.sdut.softlab.entity.Student;
import cn.edu.sdut.softlab.service.AchievementFacade;
import cn.edu.sdut.softlab.entity.Achievement;
import cn.edu.sdut.softlab.entity.Question;

/**
 * @author GaoYisheng 2017年6月6日 TODO 提交实验报告的管理类
 */
@SessionScoped
@Named("handInManager")
public class HandInManager implements Serializable {
	private static final long serialVersionUID = 7965455427888195913L;

	@Inject
	private transient Logger log;

	@Inject
	EntityManager em;

	@Inject
	LoginController loginController;

	@Inject
	AchievementFacade achievementService;

	@Inject
	private UserTransaction utx;

	private Student currentUser;// = (Student) loginController.getCurrentUser();// 当前用户

	@PostConstruct
	public void init() {
		Student s = (Student) loginController.getCurrentUser();
		this.currentUser = s;
	}

	@Inject
	FacesContext facesContext;

	public Student getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Student currentUser) {
		this.currentUser = currentUser;
	}

	Question currentQuestion;

	@RequestScoped
	@ManagedProperty(value = "#{questionManager.allQuestions}")
	private List<Question> questions;

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(Question currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	@Inject
	@SessionScoped
	private ExpReport expReport;

	public ExpReport getExpReport() {
		return expReport;
	}

	public void setExpReport(ExpReport expReport) {
		this.expReport = expReport;
	}

	private Achievement achie;

	/**
	 * ********************************************************************************
	 * core 3/3 保存\编译。并退出。 保存到数据库=<<achievement>>，保存到文件。返回至列表页
	 * 1.获取题目[PId]，学生学号[SId],[StuNO],获取文件命名[fileName]
	 * 2.获取{editor}的值[value],保存到表<achie> 的(answer)字段中
	 * 3.把 "StuNO/PId/fileName" 作为路径，保存到 表<achie>的(answerPath)中
	 * 4.开启线程，调用 #javac 、#java 命令,并获取返回值[return]
	 * 5.将返回值[return]写入表<achie> 的(result)字段中
	 * 6.比较[return]与 表<Question>中的(result)字段是否相等 => - 通过80 .eqaul()
	 *                                                           - 未通过 20 存到表<achie>的(score)字段中
	 * 7.返回到&list页面 ="todoexplist.jsf"
	 * ********************************************************************************
	 *
	 * 0x01:不把编译结果返回到页面，直接跳转到OK页面
	 */
	/**
	 * 一键编译拿到结果 2017-08-11
	 */
	public void oneBtnGetResult() throws Exception {
		// 保存
		File dirPath = new File(constitutePath());
		if (!dirPath.exists()) { // 如果目录不存在
			dirPath.mkdirs();
		}

		// 读取文件内容，并返回到前台
		String result = "";
		String errresult = "";
		File sourceFile = new File(constitutePath() + expReport.getClassName() + ".java");// 保存源代码

		try {

			if (sourceFile.exists()) {
				sourceFile.delete();
			}

			FileWriter fr = new FileWriter(sourceFile); // 将文件保存起来
			BufferedWriter bw = new BufferedWriter(fr);

			// 除标签
			String writeString = expReport.getAnswerText().replaceAll("<[^>]*>", "").replaceAll("&nbsp;", "");

			bw.write(writeString);// 将获取的代码内容存到文件中
			bw.close();
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		} // save done

		// 编译
		log.info("调用compileJava()");
		Runtime runtime = Runtime.getRuntime();
		try {
			// 将编译的错误保存下来 2>>
			String cmdCompile = "javac " + expReport.getClassName() + ".java 2> err.txt";

			String[] cmdarray = { "/bin/sh", "-c", cmdCompile };
			try {
				runtime.exec(cmdarray, null, dirPath).waitFor();

				try {

					File errFile = new File(constitutePath() + "err.txt");
					if (!errFile.exists()) {
						// 如果文件不存在，创建一个文件
						errFile.createNewFile();
					}

					InputStreamReader readerr = new InputStreamReader(new FileInputStream(errFile));// 考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(readerr);
					String errline = null;
					while ((errline = bufferedReader.readLine()) != null) {
						errresult = errresult + "\n" + errline;
					}

					readerr.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 运行
		try {
			String cmd = "java " + expReport.getClassName() + " > output.txt";
			String[] cmdarray = { "/bin/sh", "-c", cmd };
			runtime.exec(cmdarray, null, dirPath).waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 拿到返回值
		try {
			File resultFile = new File(constitutePath() + "output.txt");
			if (!resultFile.exists()) {
				// 如果文件不存在，创建一个文件
				resultFile.createNewFile();
			}

			InputStreamReader read = new InputStreamReader(new FileInputStream(resultFile));// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String line = null;
			if ((line = bufferedReader.readLine()) != null) {
				result = line;
			}
			while ((line = bufferedReader.readLine()) != null) {
				result = result + "\n" + line;
				log.info(result);
			}

			read.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		result = errresult + result;
		expReport.setResult(result);// 返回，并由button刷新到前台
	}

	public void handIn() {
		try {
			utx.begin();
			Achievement ach = new Achievement(
					expReport.getAnswerText(), 
					constitutePath(),
					expReport.getResult(),
					calculateScore(),
					currentQuestion, 
					currentUser);
			achievementService.create(ach);
			
			
			
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the achie
	 */
	public Achievement getAchie() {
		return achie;
	}

	/**
	 * @param achie
	 *            the achie to set
	 */
	public void setAchie(Achievement achie) {
		this.achie = achie;
	}

	/**
	 * @return the exp
	 */
	public ExpReport getExp() {
		return expReport;
	}

	/**
	 * @param exp
	 *            the exp to set
	 */
	public void setExp(ExpReport exp) {
		this.expReport = exp;
	}

	public void selectedChanged(ValueChangeEvent event) {
		facesContext.addMessage(null, new FacesMessage("当前问题是： " + event.getNewValue().toString()));
	}

	private String constitutePath() {
//		可以运行脚本获取当前用户,但是太不合理了. $USER可以手动替换任意用户名,部署的时候改为服务器用户名即可.
//		return "/home/$USER/ejosData/" + currentUser.getId() + "/" + currentQuestion.getId() + "/";
		
		//这个目录是在$wildfly_home目录下
		return "ejosData/" + currentUser.getId() + "/" + currentQuestion.getId() + "/";
	}

	// 计算成绩的 算法，是什么好呢？
	private int calculateScore() {
		
		//compare two result
		if (expReport.getResult().equals(currentQuestion.getResult())) {
			return 80;
		} else {
			return 50;
		}

	}
}
