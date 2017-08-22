/*
 * file_name: AnswerTextValidator.java 
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

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

import cn.edu.sdut.softlab.controller.ExpReport;

/**
 * @author GaoYisheng 2017年8月22日 TODO
 */

@FacesValidator("cn.edu.sdut.softlab.validator.AnswerTextValidator")
public class AnswerTextValidator implements Validator {

	@Inject
	@SessionScoped
	private ExpReport expReport;

	public ExpReport getExpReport() {
		return expReport;
	}

	public void setExpReport(ExpReport expReport) {
		this.expReport = expReport;
	}

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		if (expReport.getAnswerText().trim().isEmpty() || expReport.getAnswerText().contains("//")) {
			FacesMessage msg = new FacesMessage("您输入的代码为空或者含有不行注释\"//\"字符！");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
		}
	}
}
