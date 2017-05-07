/*
 * file_name: AbstractFacade.java 
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

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * @author GaoYisheng 
 * 2017年5月5日
 * 抽象接口
 */
public abstract class AbstractFacade<T> {

//	@Inject
	EntityManager em;

//	@Inject @Any

	Logger log;

	private final Class<T> entityClass;

	public AbstractFacade(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	public void create(T entity) {
		em.persist(entity);
	}

	public T edit(T entity) {
		return em.merge(entity);
	}

	public void remove(T entity) {
		em.remove(em.merge(entity));
	}

	public T find(Object id) {
		return em.find(entityClass, id);
	}

	/**
	 * 返回全部记录.
	 *
	 * @return 全部记录
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findAll() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return em.createQuery(cq).getResultList();
	}

	/**
	 * 返回给定区间的记录集合.
	 *
	 * @param startPosition
	 *            起始索引
	 * @param size
	 *            记录条数
	 * @return 记录集合
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<T> findRange(int startPosition, int size) {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		Query query = em.createQuery(cq);
		query.setMaxResults(size);
		query.setFirstResult(startPosition);
		return query.getResultList();
	}

	/**
	 * 统计总的记录数.
	 *
	 * @return 记录数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int count() {
		CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
		Root<T> rt = cq.from(entityClass);
		cq.select(em.getCriteriaBuilder().count(rt));
		Query query = em.createQuery(cq);
		return ((Long) query.getSingleResult()).intValue();
	}

	/**
	 * 根据给定的namedQuery返回记录结果.
	 *
	 * @param namedQueryName
	 *            在T中定义的namedQuery
	 * @param classT
	 *            Entity类名
	 * @return 记录集合
	 */
	public Optional<T> findSingleByNamedQuery(String namedQueryName, Class<T> classT) {
		return findOrEmpty(() -> em.createNamedQuery(namedQueryName, classT).getSingleResult());
	}

	/**
	 * 根据给定的namedQuery和参数返回记录.
	 *
	 * @param namedQueryName
	 *            namedQuery
	 * @param parameters
	 *            参数Map
	 * @param classT
	 *            Entity类名
	 * @return 结果集
	 */
	public Optional<T> findSingleByNamedQuery(String namedQueryName, Map<String, Object> parameters, Class<T> classT) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		TypedQuery<T> query = em.createNamedQuery(namedQueryName, classT);
		rawParameters.stream().forEach((entry) -> {
			query.setParameter(entry.getKey(), entry.getValue());
		});
		return findOrEmpty(() -> query.getSingleResult());
	}

	/**
	 * 根据给定的namedQuery返回记录结果集.
	 * 
	 * @param namedQueryName
	 *            namedQuery
	 * @return 结果集
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String namedQueryName) {
		return em.createNamedQuery(namedQueryName).getResultList();
	}

	/**
	 * 根据给定的namedQuery和参数返回记录结果集.
	 * 
	 * @param namedQueryName
	 *            namedQuery
	 * @param parameters
	 *            参数Map
	 * @return 结果集
	 */
	public List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters) {
		return findByNamedQuery(namedQueryName, parameters, 0);
	}

	/**
	 * 根据给定的namedQuery返回指定数目的记录结果集.
	 * 
	 * @param queryName
	 *            namedQuery
	 * @param resultLimit
	 *            返回的最多记录数
	 * @return 结果集
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String queryName, int resultLimit) {
		return em.createNamedQuery(queryName).setMaxResults(resultLimit).getResultList();
	}

	/**
	 * 根据给定的namedQuery、参数返回指定数目的记录结果集.
	 * 
	 * @param namedQueryName
	 *            namedQuery
	 * @param parameters
	 *            参数Map
	 * @param resultLimit
	 *            返回的最多记录数
	 * @return 结果集
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByNamedQuery(String namedQueryName, Map<String, Object> parameters, int resultLimit) {
		Set<Entry<String, Object>> rawParameters = parameters.entrySet();
		Query query = em.createNamedQuery(namedQueryName);
		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}
		rawParameters.stream().forEach((entry) -> {
			query.setParameter(entry.getKey(), entry.getValue());
		});
		return query.getResultList();
	}

	/**
	 * 通用方法.
	 * 
	 * @param <T>
	 *            泛型类.
	 * @param retriever
	 *            ？
	 * @return ？
	 * 
	 * @TODO 此方法如果为static会怎样？
	 */
	@SuppressWarnings("hiding")
	public <T> Optional<T> findOrEmpty(final DaoRetriever<T> retriever) {
		try {
			return Optional.of(retriever.retrieve());
		} catch (NoResultException ex) {
			log.log(Level.WARNING, "no result found:{0}", ex.getMessage());
		}
		return Optional.empty();
	}

	@FunctionalInterface
	public interface DaoRetriever<T> {
		T retrieve() throws NoResultException;
	}

}
