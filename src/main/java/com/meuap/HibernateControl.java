package com.meuap;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.meuap.objects.GenericAbstractObject;
import com.meuap.objects.building.AppartmentObject;
import com.meuap.objects.building.BuildingObject;
import com.meuap.objects.configs.GeneralConfigObject;
import com.meuap.objects.configs.HomeConfigObject;
import com.meuap.objects.gerencianet.GerencianetBoletoPaymentObject;
import com.meuap.objects.gerencianet.NotificationPaymentObject;
import com.meuap.objects.gerencianet.StateTransactionPaymentObject;
import com.meuap.objects.info.InfoKeyObject;
import com.meuap.objects.info.InfoValueObject;
import com.meuap.objects.items.ItemObject;
import com.meuap.objects.payment.AdjustmentObject;
import com.meuap.objects.payment.PaymentBoletoObject;
import com.meuap.objects.payment.PaymentObject;
import com.meuap.objects.user.UserObject;
import com.meuap.objects.user.UserSegmentObject;
import com.meuap.utils.CustomBeanUtils;
import com.meuap.utils.LoggerFactory;

@ApplicationScoped
public class HibernateControl {

	private static LoggerFactory logger = LoggerFactory.getInstance(HibernateControl.class);

	private SessionFactory factory;

	public HibernateControl() {
		try {
			Configuration configuration = new Configuration();

			String path = "com/meuap/hibernate/development/hibernate.cfg.xml";

			String dns = System.getenv("OPENSHIFT_GEAR_DNS");
			if (StringUtils.isNotBlank(dns) && dns.equals("meuap-tagliari.rhcloud.com")) {
				path = "com/meuap/hibernate/production/hibernate.cfg.xml";
			}

			logger.info("The hibernate.cfg.xml was captured from this path eq=" + path);

			configuration.configure(path);
			configuration.addAnnotatedClass(GenericAbstractObject.class);
			configuration.addAnnotatedClass(AppartmentObject.class);
			configuration.addAnnotatedClass(BuildingObject.class);
			configuration.addAnnotatedClass(GeneralConfigObject.class);
			configuration.addAnnotatedClass(HomeConfigObject.class);
			configuration.addAnnotatedClass(NotificationPaymentObject.class);
			configuration.addAnnotatedClass(StateTransactionPaymentObject.class);
			configuration.addAnnotatedClass(GerencianetBoletoPaymentObject.class);
			configuration.addAnnotatedClass(InfoKeyObject.class);
			configuration.addAnnotatedClass(InfoValueObject.class);
			configuration.addAnnotatedClass(ItemObject.class);
			configuration.addAnnotatedClass(AdjustmentObject.class);
			configuration.addAnnotatedClass(PaymentBoletoObject.class);
			configuration.addAnnotatedClass(PaymentObject.class);
			configuration.addAnnotatedClass(UserObject.class);
			configuration.addAnnotatedClass(UserSegmentObject.class);

			/**
			 * This implementation below is not used anymore because the package
			 * was not found when the .war is generated for (Class<?> cls :
			 * getEntityClassesFromPackage("com.meuap.objects")) {
			 * configuration.addAnnotatedClass(cls); }
			 **/

			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			factory = configuration.buildSessionFactory(builder.build());

		} catch (Exception e) {
			logger.error("Failed to create sessionFactory object." + e);
			e.printStackTrace();
		}
	}

	/** Insert all classes automatically into configuration **/
	public static List<Class<?>> getEntityClassesFromPackage(String packageName) throws ClassNotFoundException, IOException, URISyntaxException {
		List<Class<?>> classes = new ArrayList<Class<?>>();

		Map<String, ArrayList<String>> names = getClassAndPackageNamesFromPackage(packageName);

		if (!names.get("classes").isEmpty()) {
			for (String className : names.get("classes")) {
				Class<?> cls = Class.forName(packageName + "." + className);
				Annotation[] annotations = cls.getAnnotations();

				for (Annotation annotation : annotations) {
					logger.info(cls.getCanonicalName() + ": " + annotation.toString());
					if (annotation instanceof javax.persistence.Entity) {
						classes.add(cls);
					}
				}
			}
		}

		if (!names.get("packages").isEmpty()) {
			Iterator<String> iterator = names.get("packages").iterator();
			while (iterator.hasNext()) {
				String nextPackage = iterator.next();
				classes.addAll(getEntityClassesFromPackage(packageName.concat(".").concat(nextPackage)));
			}
		}

		return classes;
	}

	public static Map<String, ArrayList<String>> getClassAndPackageNamesFromPackage(String packageName) throws IOException, URISyntaxException, ClassNotFoundException {
		Map<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		ArrayList<String> classNames = new ArrayList<String>();
		ArrayList<String> packageNames = new ArrayList<String>();

		packageName = packageName.replace(".", "/");
		URL packageURL = classLoader.getResource(packageName);

		URI uri = new URI(packageURL.toString());
		File folder = new File(uri.getPath());
		File[] files = folder.listFiles();

		logger.info("Files eq=" + files);

		if (files != null) {
			for (File file : files) {
				String name = file.getName();
				if (name.indexOf(".class") > 0) {
					name = name.substring(0, name.lastIndexOf('.')); // remove
																		// ".class"
					classNames.add(name);
				} else {
					packageNames.add(name);
				}
			}
		}

		map.put("classes", classNames);
		map.put("packages", packageNames);

		return map;
	}

	public void persist(Object object) throws Exception {

		if (object == null) {
			return;
		}

		try {

			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			session.persist(object);
			transaction.commit();

		} catch (Exception e) {
			logger.error("Fail to persist element of class eq=" + object.getClass() + " :(", e);

			throw e;
		}
	}

	public void delete(Object object) throws Exception {

		if (object == null) {
			return;
		}

		try {

			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			session.delete(session.contains(object) ? object : session.merge(object));
			transaction.commit();

		} catch (Exception e) {
			logger.error("Fail to delete element of class eq=" + object.getClass() + " :(", e);

			throw e;
		}
	}

	public void update(Object object) throws Exception {

		if (object == null) {
			return;
		}

		try {
			Method method = object.getClass().getMethod("getId");
			Long id = (Long) method.invoke(object);

			Session session = getSession();
			Transaction transaction = session.beginTransaction();
			Object loaded = session.get(object.getClass(), id);

			CustomBeanUtils cbu = new CustomBeanUtils();
			cbu.copyProperties(loaded, object);

			session.merge(loaded);

			transaction.commit();

		} catch (Exception e) {
			logger.error("Fail to update element of class eq=" + object.getClass() + " :(", e);

			throw e;
		}
	}

	public Session getSession() {
		return factory.openSession();
	}
}
