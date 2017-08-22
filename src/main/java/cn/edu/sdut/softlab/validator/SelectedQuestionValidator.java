/*
 * file_name: SelectedItemValidator.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年8月22日 
 *       https://www.gaoyisheng.site
 *       https://github.com/timo1160139211
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.edu.sdut.softlab.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author GaoYisheng 2017年8月22日 TODO
 */
@FacesValidator("cn.edu.sdut.softlab.validator.SelectedQuestionValidator")
public class SelectedQuestionValidator implements Validator {
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if (value.equals("请选择问题：")) {
			FacesMessage msg = new FacesMessage("请选择一个题目！");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			throw new ValidatorException(msg);
		}

	}

}