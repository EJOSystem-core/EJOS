/*
 * file_name: TeacherFacade.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年5月18日 
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

import cn.edu.sdut.softlab.entity.Teacher;
import java.util.List;

/**
 * @author GaoYisheng 2017年5月18日 TODO
 */
@Named("teacherfacade")
public class TeacherFacade extends AbstractFacade<Teacher> {

    public TeacherFacade() {
        super(Teacher.class);
    }

    public Teacher findByTeaNO(BigInteger bigInteger) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("teacherNO", bigInteger);
        return findSingleByNamedQuery("teacher.findByTeaNO", parameters, Teacher.class).get();
    }
    
    public Teacher findByTeacherNoAndPassword(BigInteger teacherNum, String password) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("teaNO", teacherNum);
        parameters.put("password", password);
        return findSingleByNamedQuery("Teacher.findByTeaNOAndPassword", parameters, Teacher.class).get();
    }

    public Teacher findByTeaId(int id) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("id", id);
        return findSingleByNamedQuery("Teacher.findById", parameters, Teacher.class).get();
    }
    
    public Teacher findByTeaName(String name) {
        Map<String, Object> parameters = new HashMap<>(0);
        parameters.put("name", name);
        return findSingleByNamedQuery("Teacher.findByName", parameters, Teacher.class).get();
    }

}
