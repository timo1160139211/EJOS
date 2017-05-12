/*
 * file_name: LoginController.java 
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
package cn.edu.sdut.softlab.controller;

import cn.edu.sdut.softlab.entity.*;
import cn.edu.sdut.softlab.qualifiers.LoggedIn;
import cn.edu.sdut.softlab.service.UserFacade;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author GaoYisheng 
 * 2017年5月5日
 * 用户登录的控制类
 */
/*@SessionScoped*/
@Named("login")
public class LoginController implements Serializable  {
	
	private static final long serialVersionUID = 7965455427888195913L;

//	@Inject
//	private Credentials credentials;

	@Inject
	UserFacade userService;

	@Inject
	FacesContext facesContext;

	private Teacher currentUser = null;

	/**
	 * 处理登录逻辑.
	 */
/*	public void login() {
		Teacher stuff = userService.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
		if (stuff != null) {
			currentUser = stuff;
			facesContext.addMessage(null, new FacesMessage("Welcome, " + currentUser.getName()));
		}
	}*/

	/**
	 * 处理退出登录逻辑.
	 */
	public void logout() {
		facesContext.addMessage(null, new FacesMessage("Goodbye, " + currentUser.getName()));
		currentUser = null;
	}

	/**
	 * 判断用户是否登录.
	 *
	 * @return true：已经登录；false：没有登录
	 */
	public boolean isLoggedIn() {
		return currentUser != null;
	}

	@Produces
	@LoggedIn
	public Teacher getCurrentUser() {
		return currentUser;
	}


}
