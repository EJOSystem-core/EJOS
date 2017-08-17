/*
 * file_name: StudentFacade.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年5月10日 
 *       https://www.gaoyisheng.site
 *       https://github.com/timo1160139211
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.sdut.softlab.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Named;

import cn.edu.sdut.softlab.entity.Student;
import java.util.List;

/**
 * @author GaoYisheng 2017年5月10日 TODO
 */
@Named("studentfacade")
public class StudentFacade extends AbstractFacade<Student> {

    public StudentFacade() {
        super(Student.class);
    }

    public Student findByStuId(Integer id) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("id", id);
        return findSingleByNamedQuery("Student.findById", parameters, Student.class).get();
    }

    /**
     * 2017-05-10
     *
     * @param stuNO
     * @param password
     * @return
     */
    public Student findByStuNOAndPassword(BigInteger stuNO, String password) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("stuNO", stuNO);
        parameters.put("password", password);
        return findSingleByNamedQuery("Student.findByStuNOAndPassword", parameters, Student.class).get();
    }
    
    /**
     * 根据给定的班级信息查询学生
     * @param stu_team_id
     * @return 
     */
    public List<Student> findByTeam(Integer stu_team_id) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("stu_team_id", stu_team_id);
        return findByNamedQuery("Student.findByTeam", parameters, 0);
    }

    public Student findStudentByName(String name){
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("name", name);
        return findSingleByNamedQuery("Student.findByName", parameters, Student.class).get();
    }
    
}
