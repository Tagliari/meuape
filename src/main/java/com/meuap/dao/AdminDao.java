package com.meuap.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.meuap.HibernateControl;
import com.meuap.exception.DaoException;
import com.meuap.objects.building.AppartmentObject;
import com.meuap.objects.building.BuildingObject;
import com.meuap.objects.user.UserObject;
import com.meuap.utils.CommonUtils;
import com.meuap.utils.LoggerFactory;

@ApplicationScoped
@TransactionManagement(value = TransactionManagementType.BEAN)
public class AdminDao {

	private static LoggerFactory logger = LoggerFactory.getInstance(AdminDao.class);

	@Inject
	private HibernateControl hibernateControl;

	public AdminDao() {
	}

	public void createAppartment(AppartmentObject appartmentObject) {
		try {

			if (appartmentObject.getBuilding() != null && appartmentObject.getBuilding().getId() != null) {

				/** Will find building into database again **/
				BuildingObject findBuilding = findBuilding(appartmentObject.getBuilding());

				if (findBuilding != null) {
					appartmentObject.setBuilding(findBuilding);
					hibernateControl.getSession().merge(appartmentObject);
					hibernateControl.persist(appartmentObject);
				}
			} else {
				logger.error("Fail to persist appartment :(");
			}

		} catch (Exception e) {
			logger.error("Fail to persist appartment :(", e);
		}
	}

	public void createRenter(UserObject renter) throws DaoException {
		try {
			hibernateControl.persist(renter);
		} catch (Exception e) {
			logger.error("Fail to create Renter :(", e);

			throw new DaoException(e);
		}
	}

	@Transactional
	public void createBuilding(BuildingObject building) throws DaoException {
		try {
			hibernateControl.persist(building);
		} catch (Exception e) {
			logger.error("Fail to create building :(", e);

			throw new DaoException(e);
		}
	}

	public void deleteBuilding(BuildingObject building) throws DaoException {
		try {
			hibernateControl.delete(building);
		} catch (Exception e) {
			logger.error("Fail to delete building :(", e);

			throw new DaoException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<BuildingObject> listAllBuildings() {

		try {

			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(BuildingObject.class);

			criteria = criteria.addOrder(Order.desc("lastUpdatedDate"));
			List<BuildingObject> buildingList = criteria.list();

			if (buildingList != null) {
				return buildingList;
			}

		} catch (Exception e) {
			logger.error("Fail to list all buildings :(", e);
		}
		return new ArrayList<BuildingObject>();

	}

	public void updateBuilding(BuildingObject buildingObject) throws DaoException {

		try {
			hibernateControl.update(buildingObject);

		} catch (Exception e) {
			logger.error("Fail to update building :(", e);

			throw new DaoException(e);
		}

	}

	public BuildingObject findBuilding(BuildingObject buildingObject) {
		try {
			return (BuildingObject) hibernateControl.getSession().get(BuildingObject.class, buildingObject.getId());

		} catch (Exception e) {
			logger.error("Fail to find a building :(", e);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public List<AppartmentObject> listAllAppartments() {
		try {
			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(AppartmentObject.class);

			criteria = criteria.addOrder(Order.desc("lastUpdatedDate"));
			List<AppartmentObject> appartmentList = criteria.list();

			if (appartmentList != null) {
				return appartmentList;
			}

		} catch (Exception e) {
			logger.error("Fail to list all appartments :(", e);
		}
		return new ArrayList<AppartmentObject>();

	}

	public void createUser(UserObject userObject) throws DaoException {
		try {

			List<AppartmentObject> allAppartments = new ArrayList<AppartmentObject>();

			if (userObject.getAppartmentList() == null) {
				logger.error("Fail to persist appartment :(");
				return;
			}

			Iterator<AppartmentObject> iterator = userObject.getAppartmentList().iterator();
			while (iterator.hasNext()) {
				AppartmentObject appartment = iterator.next();

				if (appartment.getId() != null && StringUtils.isEmpty(appartment.getNumber())) {
					AppartmentObject findAppartment = findAppartment(appartment);
					if (findAppartment != null) {
						allAppartments.add(findAppartment);
					}
				} else {
					allAppartments.add(appartment);
				}
			}
			/** Removing non numerical **/
			if (userObject.getCpf() != null) {
				userObject.setCpf(userObject.getCpf().replaceAll("[^0-9]", ""));
			}
			if (userObject.getCnpj() != null) {
				userObject.setCnpj(userObject.getCnpj().replaceAll("[^0-9]", ""));
			}

			/** Actually just CPF field is used and will be validate **/
			userObject.setPersonalType("natural-person");
			String cpfcpnj = userObject.getCpf();
			boolean isCnpj = CommonUtils.validateCNPJ(cpfcpnj);
			if (isCnpj) {
				userObject.setPersonalType("juridical-person");
			}

			/** Will find building into database again **/
			if (!allAppartments.isEmpty()) {
				userObject.setAppartmentList(allAppartments);
				hibernateControl.persist(userObject);
			}

		} catch (Exception e) {
			logger.error("Fail to persist user :(", e);

			throw new DaoException(e);
		}
	}

	public AppartmentObject findAppartment(AppartmentObject appartment) {
		try {

			Session session = hibernateControl.getSession();
			Criteria criteria = session.createCriteria(AppartmentObject.class);

			SimpleExpression eq = (SimpleExpression) Restrictions.eq("id", appartment.getId());

			criteria = criteria.add(eq);
			Object result = criteria.uniqueResult();

			if (result != null) {
				return (AppartmentObject) result;
			}

		} catch (Exception e) {
			logger.error("Fail to find a appartment :(", e);
		}

		return null;
	}

}
