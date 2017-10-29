package com.meuap.dao;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.meuap.HibernateControl;
import com.meuap.controller.UserController;
import com.meuap.objects.configs.HomeConfigObject;
import com.meuap.objects.user.UserObject;
import com.meuap.objects.user.UserSegmentObject;
import com.meuap.session.AuthenticatedUserSession;
import com.meuap.utils.LoggerFactory;

@ApplicationScoped
public class ConfigDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private static LoggerFactory logger = LoggerFactory.getInstance(UserDao.class);

	private HibernateControl hibernateControl;
	private UserController userController;

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	public ConfigDao(HibernateControl hibernateControl, UserController userController) {
		this.hibernateControl = hibernateControl;
		this.userController = userController;
	}

	public ConfigDao() {

	}

	public void createHomeConfiguration(HomeConfigObject homeConfigObject) {
		logger.info("Creating or Updating HomeConfiguration");
		this.entityManager.persist(homeConfigObject);
	}

	public HomeConfigObject loadHomeConfig() {
		hibernateControl =  new HibernateControl();
		logger.info("Loading Home Configuration");

		Criteria criteria = hibernateControl.getSession().createCriteria(HomeConfigObject.class);

		SimpleExpression enabled = Restrictions.eq("enabled", true);
		criteria = criteria.add(enabled);

		AuthenticatedUserSession userSession = userController.getAuthenticatedUserSession();
		if (userSession.getLoggedUser() == null) {
			Object result = criteria.uniqueResult();
			return (HomeConfigObject) result;
		}

		/** If user has been logged, will get the segment to get correctly home configuration **/
		UserObject loggedUser = userController.getAuthenticatedUserSession().getLoggedUser();
		List<UserSegmentObject> segmentList = loggedUser.getSegmentList();

		Criterion segments = Restrictions.in("segmentList", segmentList);

		criteria = criteria.add(segments);

		// Query query = this.entityManager.createQuery(ConstantsSqls.SELECT_ALL_HOME_CONFIGS_BY_SEGMENT);

		return (HomeConfigObject) criteria.uniqueResult();
	}
}
