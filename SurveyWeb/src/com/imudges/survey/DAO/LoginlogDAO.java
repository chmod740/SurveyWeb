package com.imudges.survey.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imudges.survey.bean.Loginlog;

/**
 * A data access object (DAO) providing persistence and search support for
 * Loginlog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.imudges.survey.bean.Loginlog
 * @author MyEclipse Persistence Tools
 */
public class LoginlogDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(LoginlogDAO.class);
	// property constants
	public static final String USER_ID = "userId";
	public static final String LOGIN_TIME = "loginTime";
	public static final String RESULT = "result";
	public static final String IP = "ip";
	public static final String ERROR_INFO = "errorInfo";
	
	public void save(Loginlog transientInstance) {
		log.debug("saving Loginlog instance");
		Transaction transaction= getSession().beginTransaction();
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		transaction.commit();  
		getSession().flush();  
		getSession().close();
	}

	public void delete(Loginlog persistentInstance) {
		log.debug("deleting Loginlog instance");
		Transaction transaction= getSession().beginTransaction();
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
		transaction.commit();  
		getSession().flush();  
		getSession().close();
	}

	public Loginlog findById(java.lang.Integer id) {
		log.debug("getting Loginlog instance with id: " + id);
		try {
			Loginlog instance = (Loginlog) getSession().get(
					"com.imudges.survey.bean.Loginlog", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Loginlog instance) {
		log.debug("finding Loginlog instance by example");
		try {
			List results = getSession()
					.createCriteria("com.imudges.survey.bean.Loginlog")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Loginlog instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Loginlog as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByUserId(Object userId) {
		return findByProperty(USER_ID, userId);
	}

	public List findByLoginTime(Object loginTime) {
		return findByProperty(LOGIN_TIME, loginTime);
	}

	public List findByResult(Object result) {
		return findByProperty(RESULT, result);
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findAll() {
		log.debug("finding all Loginlog instances");
		try {
			String queryString = "from Loginlog";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Loginlog merge(Loginlog detachedInstance) {
		log.debug("merging Loginlog instance");
		try {
			Loginlog result = (Loginlog) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Loginlog instance) {
		log.debug("attaching dirty Loginlog instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Loginlog instance) {
		log.debug("attaching clean Loginlog instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public List getLastList(Object value){
		try {
			
			String queryString = "from Loginlog as model where model."
					+ "userId" + "= ? order by model.loginId desc";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setMaxResults(3);//默认取3条记录
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException e) {
			// TODO: handle exception
			log.error("get LastList fail");
			throw e;
		}
	}
}