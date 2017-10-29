package com.meuap.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.meuap.dao.AdminDao;
import com.meuap.objects.building.AppartmentObject;
import com.meuap.objects.building.BuildingObject;
import com.meuap.objects.user.UserObject;
import com.meuap.utils.CommonUtils;
import com.meuap.utils.LoggerFactory;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
public class AdminController {

	private static LoggerFactory logger = LoggerFactory.getInstance(AdminController.class);

	private Result result;
	private AdminDao adminDao;
	private UserController userController;

	public AdminController() {

	}

	@Inject
	public AdminController(Result result, AdminDao adminDao, UserController userController) {
		this.result = result;
		this.adminDao = adminDao;
		this.userController = userController;
		//
		// if (!userController.getAuthenticatedUserSession().isLogged()) {
		// logger.info("The user is not logged, will be redirect the user to
		// home page! :|");
		// this.result.redirectTo(HomeController.class).home();
		// }

		// this.result.include("authenticatedUserSession",
		// userController.getAuthenticatedUserSession());
		this.result.include("authenticatedUserSession", new UserController());
	}

	@Path({ "/admin", "/admin/" })
	public void management() {

	}

	@Path({ "/admin/building", "/admin/building/" })
	public void building() {
		logger.debug("Will capture all avaiable buildings.");

		List<BuildingObject> buildingList = this.adminDao.listAllBuildings();
		this.result.include("buildingList", buildingList);
	}

	@Path({ "/admin/building/create", "/admin/building/create/" })
	@Post
	public void createNewBuilding(BuildingObject buildingObject) {

		try {
			logger.debug("Creating a new building with these values: ");
			logger.debug(ToStringBuilder.reflectionToString(buildingObject, ToStringStyle.MULTI_LINE_STYLE));

			this.adminDao.createBuilding(buildingObject);

			this.result.redirectTo(AdminController.class).building();
		} catch (Exception e) {
			logger.error("Error to create new Building!");
		}
	}

	@Path({ "/admin/building/update/{buildingObject.id}", "/admin/building/update/{buildingObject.id}/" })
	@Put
	public void updateBuilding(BuildingObject buildingObject) {

		try {
			logger.debug("Editing a building with id eq= " + buildingObject.getId());
			logger.debug(ToStringBuilder.reflectionToString(buildingObject, ToStringStyle.MULTI_LINE_STYLE));

			this.adminDao.updateBuilding(buildingObject);

			this.result.redirectTo(AdminController.class).building();

		} catch (Exception e) {
			logger.error("Error to update Building!");
		}
	}

	@Path({ "/admin/building/delete/", "/admin/building/delete", "/admin/building/delete/{buildingObject.id}", "/admin/building/delete/{buildingObject.id}/" })
	@Delete
	public void deleteBuilding(BuildingObject buildingObject) {

		try {
			logger.debug("Deleting a building with id eq=" + buildingObject.getId());
			this.adminDao.deleteBuilding(buildingObject);

			this.result.redirectTo(AdminController.class).building();

		} catch (Exception e) {
			logger.error("Error to delete Building!");
		}
	}

	@Get({ "/admin/building/find/{buildingObject.id}", "/admin/building/find/{building.id}/" })
	public void findBuilding(BuildingObject buildingObject) {
		logger.debug("Finding a building with id eq=" + buildingObject.getId());
		buildingObject = this.adminDao.findBuilding(buildingObject);

		this.result.include("buildingObject", buildingObject);
	}

	@Path({ "/admin/user", "/admin/user/" })
	public void user() {
		logger.debug("Will verify users or create new one.");

		List<AppartmentObject> appartmentList = this.adminDao.listAllAppartments();

		this.result.include("appartmentList", appartmentList);
	}

	@Path({ "/admin/user/create", "/admin/user/create/" })
	@Post
	public void createNewUser(UserObject userObject) {
		try {
			logger.debug("Creating a new user with these values: ");
			logger.debug(ToStringBuilder.reflectionToString(userObject, ToStringStyle.MULTI_LINE_STYLE));

			List<String> userFields = validateUserFields(userObject);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", userFields.size() > 0 ? false : true);
			map.put("properties", userFields);

			if (userFields.size() == 0) {
				this.adminDao.createUser(userObject);
			}
			
			result.use(Results.json()).withoutRoot().from(map).serialize();

		} catch (Exception e) {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("properties", new ArrayList<String>());
			map.put("message", e);
			result.use(Results.json()).withoutRoot().from(map).serialize();
		}
	}

	private List<String> validateUserFields(UserObject userObject) {
		List<String> properties = new ArrayList<String>();

		String cpf = userObject.getCpf();
		String cnpj = userObject.getCnpj();
		
		if (StringUtils.isBlank(cpf) && StringUtils.isBlank(cnpj)) {
			properties.add("userCPFCNPJ");
		}
			
		if (StringUtils.isNotBlank(cpf)) {
			boolean valid = CommonUtils.validateCPF(cpf.replaceAll("[^0-9]", ""));
			if (!valid) {
				properties.add("userCPF");
			}
		}

		if (StringUtils.isNotBlank(cnpj)) {
			boolean valid = CommonUtils.validateCNPJ(cnpj.replaceAll("[^0-9]", ""));
			if (!valid) {
				properties.add("userCNPJ");
			}
		}

		Date birthdate = userObject.getBirthdate();
		if (birthdate == null) {
			properties.add("userBirthdate");
		} else {
			boolean after = birthdate.after(new Date());
			if (after) {
				properties.add("userBirthdate");
			}
		}

		String displayName = userObject.getDisplayName();
		if (StringUtils.isBlank(displayName)) {
			properties.add("userDisplayName");
		} else {
			String[] name = displayName.trim().split(" ");
			if (name.length <= 1) {
				properties.add("userDisplayName");
			}
		}

		return properties;
	}

	@Path({ "/admin/user/appartment", "/admin/user/appartment/" })
	@Post
	public void newAppartmentForUser() {
		logger.debug("Form to create appartment in user: ");

		/**
		 * It is necessary to get all buildings to create appartment in
		 * createUser page
		 **/
		building();
	}

	@Path({ "/admin/appartment/create", "/admin/appartment/create/" })
	@Post
	public void createNewAppartment(AppartmentObject appartmentObject) {
		try {
			logger.debug("Creating a new appartment with these values: ");
			logger.debug(ToStringBuilder.reflectionToString(appartmentObject, ToStringStyle.MULTI_LINE_STYLE));

			if (appartmentObject == null) {
				logger.error("appartmentObject is null, please verify!");
				return;
			}

			this.adminDao.createAppartment(appartmentObject);

			logger.debug("Appartment created! ");

			this.result.redirectTo(AdminController.class).user();
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public UserController getUserController() {
		return userController;
	}

	public void setUserController(UserController userController) {
		this.userController = userController;
	}

}
