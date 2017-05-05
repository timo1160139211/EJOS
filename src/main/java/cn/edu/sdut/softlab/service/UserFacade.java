/*
 * file_name: UserFacade.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年5月5日 
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

import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Named;

import cn.edu.sdut.softlab.entity.*;

/**
 * @author GaoYisheng 
 * 2017年5月5日
 * 查询用户
 */
@Stateless
@Named("user")
public class UserFacade extends AbstractFacade<Student> {

	public UserFacade() {
		super(Student.class);
	}

	/**
	 * 同findByUsernameAndPassword()理
	 */
	public Student findByName(String username) {
		Map<String, Object> parameters = new HashMap<>(0);
		parameters.put("username", username);
		return findSingleByNamedQuery("Student.findByUsername", parameters, Student.class).get();
	}
	
	/**
	 * 同findByUsernameAndPassword()理
	 * 
	 * 以后 找回密码用：mail + pwd
	 */
/*	public Student findByMail(String mail) {
		Map<String, Object> parameters = new HashMap<>(0);
		parameters.put("mail", mail);
		*/
		/*
		 * 待 修改完善
		 * findSingleByNamedQuery => findSingleByMailQuery
		 */
/*		return findSingleByNamedQuery("Stuff.findByUsername", parameters, Student.class).get();
	}*/
	

	public Student findByUsernameAndPassword(String username, String password) {
		Map<String, Object> parameters = new HashMap<>(0);
		parameters.put("username", username);
		parameters.put("password", password);
		return findSingleByNamedQuery("Student.findByUsernameAndPassword", parameters, Student.class).get();
	}
}
