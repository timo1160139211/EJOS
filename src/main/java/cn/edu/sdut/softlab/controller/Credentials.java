/*
 * file_name: Credentials.java 
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

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
/**
 * @author GaoYisheng 
 * 2017年5月5日
 * 作为验证中转类
 */
@RequestScoped
@Named
public class Credentials {

	private String name;
	private String password;
/*	private String identity;*/
	
	public String getName() {
		return name;
	}
	public void setName(String username) {
		this.name = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//用以区别身份(学生、老师、管理员)
/*	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}*/

	
}
