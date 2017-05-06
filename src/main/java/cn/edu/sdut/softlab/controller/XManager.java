/*
 * file_name: XManager.java 
 *
 * Copyright GaoYisheng Corporation 2017 
 * 
 * License： 
 * date：2017年5月6日 
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

import java.util.List;

/**
 * @author GaoYisheng 
 * 2017年5月6日
 * SomethingManager Interface 接口
 * @param <T>
 */
public interface XManager<T> {

	/**
	 * 获得所有 Item 列表.
	 *
	 * @return 所有 Item 列表
	 * @throws Exception
	 */
	List<T> get() throws Exception;

	/**
	 * 新增 Item .
	 *
	 * @return 返回 Item 列表页面
	 * @throws Exception
	 */
	String addItem() throws Exception;

	/**
	 * @return
	 * @throws Exception
	 */
	String deleteItem() throws Exception;

}
