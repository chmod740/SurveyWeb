package com.imudges.survey.DAO;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.imudges.survey.bean.Opt;

/**
 * A data access object (DAO) providing persistence and search support for Opt
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.imudges.survey.bean.Opt
 * @author MyEclipse Persistence Tools
 */
public class OptDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(OptDAO.class);
	// property constants
	public static final String SECRET_ID = "secretId";
	public static final String SURVEY_ID = "surveyId";
	public static final String RESULT = "result";
	public static final String SUBMIT = "submit";

	public void save(Opt transientInstance) {
		log.debug("saving Opt instance");
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

	public void delete(Opt persistentInstance) {
		log.debug("deleting Opt instance");
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

	public Opt findById(java.lang.Integer id) {
		log.debug("getting Opt instance with id: " + id);
		try {
			Opt instance = (Opt) getSession().get("com.imudges.survey.bean.Opt",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Opt instance) {
		log.debug("finding Opt instance by example");
		try {
			List results = getSession()
					.createCriteria("com.imudges.survey.bean.Opt")
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
		log.debug("finding Opt instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Opt as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySecretId(Object secretId) {
		return findByProperty(SECRET_ID, secretId);
	}

	public List findBySurveyId(Object surveyId) {
		return findByProperty(SURVEY_ID, surveyId);
	}

	public List findByResult(Object result) {
		return findByProperty(RESULT, result);
	}

	public List findBySubmit(Object submit) {
		return findByProperty(SUBMIT, submit);
	}

	public List findAll() {
		log.debug("finding all Opt instances");
		try {
			String queryString = "from Opt";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Opt merge(Opt detachedInstance) {
		log.debug("merging Opt instance");
		try {
			Opt result = (Opt) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Opt instance) {
		log.debug("attaching dirty Opt instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Opt instance) {
		log.debug("attaching clean Opt instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}