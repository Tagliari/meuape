package com.meuap.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.meuap.HibernateControl;
import com.meuap.objects.user.UserObject;
import com.meuap.utils.LoggerFactory;

@SessionScoped
public class UserDao implements Serializable {

	private static final long serialVersionUID = 1L;
	private static LoggerFactory logger = LoggerFactory.getInstance(UserDao.class);

	private HibernateControl hibernateControl;

	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	public UserDao(HibernateControl hibernateControl) {
		this.hibernateControl = hibernateControl;
	}

	public UserDao() {

	}

	public UserObject loadUser(UserObject userObject) {
		logger.info("Doing login for user eq="
				+ ToStringBuilder.reflectionToString(userObject, ToStringStyle.MULTI_LINE_STYLE));

		Criteria criteria = hibernateControl.getSession().createCriteria(UserObject.class);

		SimpleExpression email = Restrictions.eq("email", userObject.getEmail());
		SimpleExpression password = Restrictions.eq("password", userObject.getPassword());

		criteria = criteria.add(email).add(password);
		Object result = criteria.uniqueResult();

		return (UserObject) result;
	}

	public boolean registerAdminUser(UserObject userObject) {
		logger.info("Doing register for user eq="
				+ ToStringBuilder.reflectionToString(userObject, ToStringStyle.MULTI_LINE_STYLE));

		try {
			if (userObject.getEmail().isEmpty()) {
				return false;
			}

			if (userObject.getPassword().isEmpty()) {
				return false;
			}

			userObject.setAccountType("admin");

			this.entityManager.persist(userObject);

			return true;
		} catch (Exception e) {
			logger.error("", e);
		}

		return false;
	}

	public UserObject findUser(UserObject user) {
		try {

			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(UserObject.class);

			SimpleExpression eq = (SimpleExpression) Restrictions.eq("id", user.getId());

			criteria = criteria.add(eq);
			Object result = criteria.uniqueResult();

			if (result != null) {
				return (UserObject) result;
			}

		} catch (Exception e) {
			logger.error("Fail to find user :(", e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<UserObject> listAllUsers() {
		try {
			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(UserObject.class);

			criteria = criteria.addOrder(Order.desc("displayName"));
			List<UserObject> userList = criteria.list();

			if (userList != null) {
				return userList;
			}

		} catch (Exception e) {
			logger.error("Fail to list all users :(", e);
		}
		return new ArrayList<UserObject>();

	}
}
